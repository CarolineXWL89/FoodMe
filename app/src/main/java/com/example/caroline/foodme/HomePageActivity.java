package com.example.caroline.foodme;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.caroline.foodme.FavoritesFragment.FavoritesFragment;
import com.example.caroline.foodme.GenerateFragment.AutoGenerateFragment;
import com.example.caroline.foodme.SearchFragment.SearchFragment;
import com.example.caroline.foodme.SetUp.AccountSetUpActivity;
import com.example.caroline.foodme.SetUp.ItemSetUpActivity;
import com.example.caroline.foodme.UserInfo.LoginScreen;
import com.example.caroline.foodme.UserInfo.SettingsPageActivity;

/*
Contains stuff like recently added seen after logging in (NOT 1st time)
Implements: accessing ALL users' entries
Contains: scrolling image gallery; access toolbar for fav, add, search; settings icon
Can: be accessed by clicking on logo/home, NOT launching activity!!! (Need to change)
 */
public class HomePageActivity extends AppCompatActivity {
    //
    /*
    GET USERID //todo delete user exists when you log out
    SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String userID = sharedPref.getString(getString(R.string.user_ID), "");*/
    public static final String TAG = "HomePageActivity";
    private View decorView;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private SearchView searchView;
    private Fragment currentFragment;
    private FragmentManager fragmentManager;

    public static Context getContext() {
        return context;
    }

    private static Context context;


    //todo app icon use adobe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Backendless.initApp(this, BackendlessSettings.APP_ID, BackendlessSettings.API_KEY);
        logIn(); //checks if user ahs already logged in, if not switches to log in screen
    }

    @Override
    protected void onResume() {
        hideNavBar();
        super.onResume();
    }

    public void hideNavBar() {
        //hides navigation bar so app can be fullscreen
        decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        //todo call again after keyboard is pulled up?
    }

    private void logIn() {
        if(Backendless.UserService.CurrentUser() == null) {
            //todo fix so it doesn't just log in to tester account
            Backendless.UserService.login("h", "h", new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            Toast.makeText(HomePageActivity.this, "Welcome "+ response.getProperty("name")+ "!", Toast.LENGTH_SHORT).show();
                            wireWidgets();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.d(TAG, "handleFault: "+fault.getMessage());
                        }
                    });
            /*Intent i = new Intent(this, LoginScreen.class);
            startActivity(i);*/
        }
        /* //todo fix login and user stuff (basically if remembe rme login for them rather than )
        sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int userExists = sharedPref.getInt(getString(R.string.user), 0);
        //userExists = 0;
        //checks if previous user exists
        if (userExists == 0) {
            Intent i = new Intent(this, LoginScreen.class);
            startActivity(i);
        }
        if (userExists == 1) {
            editor.clear();
            editor.putInt(getString(R.string.user), 0);
            editor.commit();
            Toast.makeText(this, "Next time you'll need to login again", Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_action_bar, menu);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search_recipe_general).getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE); //todo make search view take up whole bar and onclick open keyboard
        return true;
    }

    private void wireWidgets() {
        //creates toolbar at top for settings icon
        Toolbar myToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(myToolbar);
        //creates bottom navigation bar to hold the fragment navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getSupportFragmentManager();
        currentFragment = new FavoritesFragment();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, currentFragment).commit();
        hideNavBar();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //Prepare a null fragment
            currentFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    currentFragment = new SearchFragment();
                    //todo sort by mine, api, alphabetically? idk what else
                    break;
                case R.id.navigation_favorites:
                    currentFragment = new FavoritesFragment();
                    break;
                case R.id.navigation_create:
                    //todo alert dialog upload recipe or auto-generate?
                    currentFragment = new NewRecipeOptionsFragment();//new CreateFragment();
                    break;
            }
            //transmits proper fragment
            fragmentManager = getSupportFragmentManager();
            if (currentFragment != null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, currentFragment)
                        .commit();
                return true;
            }
            return false;
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                //enters settings activity
                Intent i = new Intent(this, SettingsPageActivity.class);
                startActivity(i);
                return true;
            case R.id.search_recipe_general:
                //begins searching
                //todo shift focus
            default:
                return super.onOptionsItemSelected(item);
        }
    }

@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
package com.example.caroline.foodme.UserInfo;

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
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.caroline.foodme.BackendlessSettings;
import com.example.caroline.foodme.CreateFragment.CreateFragment;
import com.example.caroline.foodme.FavoritesFragment.FavoritesFragment;
import com.example.caroline.foodme.R;
import com.example.caroline.foodme.SearchFragment.SearchFragment;


//todo search bar focus

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
    public static final String TAG = "YADA";
    private TextView mTextMessage;
    private View decorView;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Backendless.initApp(this, BackendlessSettings.APP_ID, BackendlessSettings.API_KEY);
        logIn(); //checks if user ahs already logged in, if not switches to log in screen
        wireWidgets();
        hideNavBar();
        currentFragment = new FavoritesFragment();
        FragmentManager fm = getSupportFragmentManager();
        if(currentFragment != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, currentFragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        hideNavBar();
        super.onResume();
    }

    public void hideNavBar(){
        decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        //todo call when keyboard goes down
    }

    private void logIn() {
        sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int userExists = sharedPref.getInt(getString(R.string.user), 0);
        Log.d("userExists", userExists+"");
//        userExists = 0; //todo delete me later
// //checks if previous user exists
        if (userExists == 0) {
            Intent i = new Intent(this, LoginScreen.class);
            startActivity(i);
        }
        if (userExists == 1) {
            editor.remove(getString(R.string.user));
            editor.putInt(getString(R.string.user), 0);
            editor.commit();
            Toast.makeText(this, "Next time you'll need to login again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_action_bar, menu);
        return true;
    }

    private void wireWidgets() {
        Log.d(TAG, "wireWidgets: ");
        //creates toolbar at top for settings icon
        Toolbar myToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(myToolbar);
        //wires bottom navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                    //needs sort by (sorts results)
                    break;
                case R.id.navigation_favorites:
                    currentFragment = new FavoritesFragment();
                    break;
                case R.id.navigation_create:
                    currentFragment = new CreateFragment();
                    break;
            }
            FragmentManager fm = getSupportFragmentManager();
            if(currentFragment != null) {
                fm.beginTransaction()
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
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

//
}

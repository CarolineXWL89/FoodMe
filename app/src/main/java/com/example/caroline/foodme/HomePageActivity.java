package com.example.caroline.foodme;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;
/*
Contains stuff like recently added seen after logging in (NOT 1st time)
Implements: accessing ALL users' entries
Contains: scrolling image gallery; access toolbar for fav, add, search; settings icon
Can: be accessed by clicking on logo/home, NOT launching activity!!! (Need to change)
 */
public class HomePageActivity extends AppCompatActivity{

    /*
    GET USERID //todo delete user exists when you log out
    SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String userID = sharedPref.getString(getString(R.string.user_ID), "");*/
    public static final String TAG = "HomePageActivity";
    private TextView mTextMessage;
    private View decorView;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        logIn(); //checks if user ahs already logged in, if not switches to log in screen
        wireWidgets();
        Backendless.initApp( this, BackendlessSettings.APP_ID, BackendlessSettings.API_KEY );
        hideNavBar();
    }

    @Override
    protected void onResume() {
        hideNavBar();
        super.onResume();
    }

    public void hideNavBar(){
        decorView=getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);


    }


    private void logIn() {
        sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        Boolean userExists = sharedPref.getBoolean(getString(R.string.user), false); //checks if previous user exists

        userExists = true; //todo delete me later
        if(!userExists){ //if no user sends you to login
            Intent i = new Intent(this, LoginScreen.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_action_bar, menu);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search_recipe_general).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                    doMySearch(query);
                    hideNavBar();
                    return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return true;
    }

    private void wireWidgets() {
        Log.d(TAG, "wireWidgets: ");
        //creates toolbar at top for settings icon
        Toolbar myToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(myToolbar);
        //todo delete once fragments are completely done
        //wires bottom navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            //Prepare a null fragment
            Fragment currentFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    currentFragment = new SearchFragment();
                    //needs serach by (name or ingrediates)
                    //needs sort by (sorts results)
                    //needs recent seraches
                    //needs recent views
                    break;
                case R.id.navigation_favorites:
                    currentFragment = new FavoritesFragment();
                    break;
                case R.id.navigation_create:
                    currentFragment = new CreateFragment();
                    break;
            }
            FragmentManager fm = getSupportFragmentManager();
            if(currentFragment != null)
            {
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
    private void doMySearch(String query) {
        //todo make call to backendless and display as recycler view
        //todo get user id
        StringBuilder whereClause = new StringBuilder();
        //whereClause.append( "recipeName like '%Bread%'" );
        whereClause.append("recipeName like '%" + query + "%'");
        // String whereClause="recipeName = '"+query+"'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause.toString());
        Backendless.Data.of(Recipe.class).find(queryBuilder, new AsyncCallback<List<Recipe>>() {
            @Override
            public void handleResponse(List<Recipe> response) {
                Log.d(TAG, "handleResponse: " + response.size());
                ArrayList<Recipe> recipies = new ArrayList<>();
                recipies.addAll(response);
                Intent i = new Intent(HomePageActivity.this, SearchResultsDisplayer.class);
                i.putExtra("the_stuff", recipies);
                startActivity(i);
                //searchResultsAdapter.notifyDataSetChanged();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, "handleFault: " + fault.getMessage());
            }
        });
    }

}

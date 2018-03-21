package com.example.caroline.foodme;

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

/*
Contains stuff like recently added seen after logging in (NOT 1st time)
Implements: accessing ALL users' entries
Contains: scrolling image gallery; access toolbar for fav, add, search; settings icon
Can: be accessed by clicking on logo/home, NOT launching activity!!! (Need to change)
 */
public class HomePageActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        logIn(); //checks if user ahs already logged in, if not swtiches to log in screen
        wireWidgets();




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
        if(!userExists){ //if no user sends you to login
            Intent i = new Intent(this, LoginScreen.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_action_bar, menu);
        return true;
    }

    private void wireWidgets() {
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
                    Log.d(TAG, "onNavigationItemSelected: search");
                    currentFragment = new SearchFragment();
                    break;
                case R.id.navigation_favorites:
                    Log.d(TAG, "onNavigationItemSelected: favorites");
                    currentFragment = new FavoritesFragment();
                    break;
                case R.id.navigation_create:
                    Log.d(TAG, "onNavigationItemSelected: create");
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
}

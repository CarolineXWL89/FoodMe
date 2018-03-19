package com.example.caroline.foodme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    private TextView mTextMessage;
    View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
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
        mTextMessage = (TextView) findViewById(R.id.message);
        //wires bottom navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.title_search);
                    //todo load search fragment
                    return true;
                case R.id.navigation_favorites:
                    mTextMessage.setText(R.string.title_favorites);
                    //todo load favorites fragment
                    return true;
                case R.id.navigation_create:
                    mTextMessage.setText(R.string.title_create);
                    //todo load create fragment
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


}

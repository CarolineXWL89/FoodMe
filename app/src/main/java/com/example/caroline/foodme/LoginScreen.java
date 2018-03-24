package com.example.caroline.foodme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

/*
Initial screen seen when opening app
 */
public class LoginScreen extends AppCompatActivity {

    private Button login, newAccount;
    private EditText usernameInput, passwordInput;
    private CheckBox rememberMe;
    private TextView username, password;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);



        //todo save UserID from bsckendless todo if not remember me turn it off
        /* editor = sharedPref.edit();
        editor.putString(getString(R.string.user_ID), "");
        editor.putBoolean(getString(R.string.user), true); //means there is a saved user
        editor.commit(); */

        wireWidgets();
        //TODO wire login button to HomePageActivity, newAccount to CreateAccount, set onClickListeners for all
        //TODO link to Backendless
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_action_bar, menu);
        return true;
    }

    public void wireWidgets(){
        login = (Button) findViewById(R.id.login_button);
        newAccount = (Button) findViewById(R.id.new_account_button);

        usernameInput = (EditText) findViewById(R.id.username_editText);
        passwordInput = (EditText) findViewById(R.id.password_editText);
        rememberMe = (CheckBox) findViewById(R.id.remember_me_checkBox);
        toolbar= (Toolbar)findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                //enters settings activity
                //todo  intent for help
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void setOnClickListeners(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backendless.UserService.login(usernameInput.getText().toString(), passwordInput.getText().toString(), new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        String username = (String) response.getProperty("username");
                        Toast.makeText(LoginScreen.this, "Hello " +username, Toast.LENGTH_SHORT).show();
                        
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(LoginScreen.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginScreen.this, CreateAccount.class);
                startActivity(i);
            }
        });

    }
}

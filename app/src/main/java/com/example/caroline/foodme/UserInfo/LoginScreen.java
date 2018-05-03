package com.example.caroline.foodme.UserInfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.example.caroline.foodme.R;

/*
Initial screen seen when opening app
 */
public class LoginScreen extends AppCompatActivity {

    private Button login, newAccount, help;
    private EditText usernameInput, passwordInput;
    private CheckBox rememberMe;
    private TextView username, password, forgotPassword;
    private Toolbar toolbar;
    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        wireWidgets();
    }

    public void wireWidgets(){
        login = (Button) findViewById(R.id.login_button);
        newAccount = (Button) findViewById(R.id.new_account_button);
        usernameInput = (EditText) findViewById(R.id.username_editText);
        passwordInput = (EditText) findViewById(R.id.password_editText);
        rememberMe = (CheckBox) findViewById(R.id.remember_me_checkBox);
        toolbar= (Toolbar)findViewById(R.id.toolbar_login);
        forgotPassword = (TextView) findViewById(R.id.forgot_password_textView);
        setSupportActionBar(toolbar);
        setOnClickListeners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                //todo create an activity for a help page for help
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
/* why is this here
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
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/
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
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(getString(R.string.user_ID), response.getUserId());
                        if(rememberMe.isChecked()){
                            editor.putInt(getString(R.string.user), 2); //means there is a saved user
                        }
                        else{
                            editor.putInt(getString(R.string.user), 1); //means there is a saved user, but user does not wish to be remembered.
                            Toast.makeText(LoginScreen.this, "Not remembering you", Toast.LENGTH_SHORT).show();
                        }
                        editor.commit();
                        boolean b = (boolean) response.getProperty("updatedsetup");
                        if(!b){
                            //TODO: once merged, make this go to the AccountSetUpActivity instead of HomePageActivity. Then, find a way to change the value of "updatedsetup" to "true" after the set up is completed.
                            Toast.makeText(LoginScreen.this, "This is your first time logging in!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginScreen.this, HomePageActivity.class);
                            startActivity(i);
                        }
                        else{
                            Intent i = new Intent(LoginScreen.this, HomePageActivity.class);
                            startActivity(i);

                        }
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
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!usernameInput.getText().equals(null)){
                    Backendless.UserService.restorePassword(usernameInput.getText().toString(), new AsyncCallback<Void>() {
                        @Override
                        public void handleResponse(Void response) {
                            Toast.makeText(LoginScreen.this, "An email has been sent to "+usernameInput.getText().toString()+"'s email to restore your password.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(LoginScreen.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(LoginScreen.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
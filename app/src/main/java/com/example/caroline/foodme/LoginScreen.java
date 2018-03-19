package com.example.caroline.foodme;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private Button login, newAccount, help;
    private EditText usernameInput, passwordInput;
    private CheckBox rememberMe;
    private TextView foodMe, username, password;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        /* FIX ME THROUGH APP
        //todo save when logged in, delete when logged out, remember if logged it
        sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString(getString(R.string.user_name), "");
        editor.putString(getString(R.string.pass_word), "");
        editor.commit();

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String username = sharedPref.getString(getString(R.string.user_name), "");
        String password = sharedPref.getString(getString(R.string.pass_word), "");*/


        wireWidgets();
        setOnClickListeners();
        //TODO wire login button to HomePageActivity, newAccount to CreateAccount, set onClickListeners for all
        //TODO link to Backendless
    }

    public void wireWidgets(){
        login = (Button) findViewById(R.id.login_button);
        newAccount = (Button) findViewById(R.id.new_account_button);
        help = (Button) findViewById(R.id.help_button);
        usernameInput = (EditText) findViewById(R.id.username_editText);
        passwordInput = (EditText) findViewById(R.id.password_editText);
        rememberMe = (CheckBox) findViewById(R.id.remember_me_checkBox);
        foodMe = (TextView) findViewById(R.id.food_me_textView);
        username = (TextView) findViewById(R.id.username_textView);
        password = (TextView) findViewById(R.id.password_textView);
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
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

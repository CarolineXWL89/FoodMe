package com.example.caroline.foodme;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
}

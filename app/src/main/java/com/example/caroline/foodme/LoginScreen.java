package com.example.caroline.foodme;

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
    //treeeeellow

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        wireWidgets();
        //TODO wire login button to HomePageActivity, newAccount to CreateAccount, set onClickListeners for all
        //TODO link to Backendless
    }

    public void wireWidgets(){
        login = findViewById(R.id.login_button);
        newAccount = findViewById(R.id.new_account_button);
        help = findViewById(R.id.help_button);
        usernameInput = findViewById(R.id.username_editText);
        passwordInput = findViewById(R.id.password_editText);
        rememberMe = findViewById(R.id.remember_me_checkBox);
        foodMe = findViewById(R.id.food_me_textView);
        username = findViewById(R.id.username_textView);
        password = findViewById(R.id.password_textView);
    }
}

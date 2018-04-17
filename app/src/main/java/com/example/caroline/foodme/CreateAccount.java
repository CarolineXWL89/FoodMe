package com.example.caroline.foodme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
Has user make new account; linked from New Account button in LoginScreen
 */
public class CreateAccount extends AppCompatActivity {

    private TextView createAccountScreen, firstName, mi, lastName, username, email, password, confirmPass;
    private EditText firstNameInput, miInput, lastNameInput, usernameInput, emailInput, passInput, confirmPassInput;
    private Button createAccount;
    private CheckBox terms;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        wireWidgets();

        //TODO Backendless connection, createAccount sends out confirmation email OR FOR NOW brings to Login, adds user
    }

    public void wireWidgets(){
        createAccount = (Button) findViewById(R.id.create_account_button);
        firstNameInput = (EditText) findViewById(R.id.first_name_editText);
        lastNameInput = (EditText) findViewById(R.id.last_name_edit_text);
        usernameInput = (EditText) findViewById(R.id.username_editText);
        emailInput = (EditText) findViewById(R.id.email_editText);
        passInput = (EditText) findViewById(R.id.password_editText);
        confirmPassInput = (EditText) findViewById(R.id.password_confirm_editText);
        terms = (CheckBox) findViewById(R.id.terms_checkBox);
        firstName = (TextView) findViewById(R.id.first_name_textView);
        lastName = (TextView) findViewById(R.id.last_name_text_view);
        username = (TextView) findViewById(R.id.username_editText);
        email = (TextView) findViewById(R.id.email_textView);
        password = (TextView) findViewById(R.id.password_textView);
        confirmPass = (TextView) findViewById(R.id.password_confirm_textView);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo create user in backendless and login --> bypass login screen
            }
        });
        toolbar= (Toolbar)findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
    }
}

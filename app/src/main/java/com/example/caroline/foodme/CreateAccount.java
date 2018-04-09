package com.example.caroline.foodme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/*
Has user make new account; linked from New Account button in LoginScreen
 */
public class CreateAccount extends AppCompatActivity {

    private TextView createAccountScreen, firstName, mi, lastName, username, email, password, confirmPass;
    private EditText firstNameInput, miInput, lastNameInput, usernameInput, emailInput, passInput, confirmPassInput;
    private Button createAccount;
    private CheckBox terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        wireWidgets();

        //TODO Backendless connection, createAccount sends out confirmation email OR FOR NOW brings to Login, adds user
    }

    public void wireWidgets(){
        createAccount = findViewById(R.id.create_account_button);
        firstNameInput = findViewById(R.id.first_name_editText);
        miInput = findViewById(R.id.mi_editText);
        lastNameInput = findViewById(R.id.last_name_editText);
        usernameInput = findViewById(R.id.username_editText);
        emailInput = findViewById(R.id.email_editText);
        passInput = findViewById(R.id.password_editText);
        confirmPassInput = findViewById(R.id.password_confirm_editText);
        terms = findViewById(R.id.terms_checkBox);
        createAccountScreen = findViewById(R.id.create_account_textView);
        firstName = findViewById(R.id.first_name_textView);
        mi = findViewById(R.id.middle_initial_textView);
        lastName = findViewById(R.id.last_name_textView);
        username = findViewById(R.id.username_editText);
        email = findViewById(R.id.email_textView);
        password = findViewById(R.id.password_textView);
        confirmPass = findViewById(R.id.password_confirm_textView);
    }
}

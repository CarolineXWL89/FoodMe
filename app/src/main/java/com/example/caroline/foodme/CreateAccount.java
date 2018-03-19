package com.example.caroline.foodme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.BackendlessUser;

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
        setOnCLickListeners();

        //TODO Backendless connection, createAccount sends out confirmation email OR FOR NOW brings to Login, adds user
    }

    public void wireWidgets(){
        createAccount = (Button) findViewById(R.id.create_account_button);
        firstNameInput = (EditText) findViewById(R.id.first_name_editText);
        miInput = (EditText) findViewById(R.id.mi_editText);
        lastNameInput = (EditText) findViewById(R.id.last_name_editText);
        usernameInput = (EditText) findViewById(R.id.username_editText);
        emailInput = (EditText) findViewById(R.id.email_editText);
        passInput = (EditText) findViewById(R.id.password_editText);
        confirmPassInput = (EditText) findViewById(R.id.password_confirm_editText);
        terms = (CheckBox) findViewById(R.id.terms_checkBox);
        createAccountScreen = (TextView) findViewById(R.id.create_account_textView);
        firstName = (TextView) findViewById(R.id.first_name_textView);
        mi = (TextView) findViewById(R.id.middle_initial_textView);
        lastName = (TextView) findViewById(R.id.last_name_textView);
        username = (TextView) findViewById(R.id.username_editText);
        email = (TextView) findViewById(R.id.email_textView);
        password = (TextView) findViewById(R.id.password_textView);
        confirmPass = (TextView) findViewById(R.id.password_confirm_textView);
    }

    public void setOnCLickListeners(){
        //OnClickListener for the createAccount Button, creates an account in Backendless
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmPassword()){
                    //process for creating a new user:
                    BackendlessUser user = new BackendlessUser();
                    user.setProperty("name", firstNameInput.getText().toString() + " " + miInput.getText().toString()+". "+lastNameInput.getText().toString());
                }
            }
        });
    }

    //checks to make sure the password was imputed properly in the two EditTexts
    public boolean confirmPassword(){
        if(passInput.getText().toString().equals(confirmPassInput.getText().toString())){
            return true;
        }
        else{
            Toast.makeText(CreateAccount.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

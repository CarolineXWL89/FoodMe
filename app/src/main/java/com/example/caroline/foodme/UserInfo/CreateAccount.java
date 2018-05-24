package com.example.caroline.foodme.UserInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.caroline.foodme.R;

/*
Has user make new account; linked from New Account button in LoginScreen
 */
public class CreateAccount extends AppCompatActivity {

    private TextView createAccountScreen, firstName, mi, lastName, username, email, password, confirmPass, termsOfUse;
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
        termsOfUse = findViewById(R.id.terms_textView);
        toolbar= (Toolbar)findViewById(R.id.toolbar_create_account);
        setSupportActionBar(toolbar);
        termsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAccount.this, TermsOfUseActivity.class);
                startActivity(i);
            }
        });
        //todo make sure all of these have been filled out
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmPassword()){
                    //process for creating a new user:
                    BackendlessUser user = new BackendlessUser();
                    //                    user.setProperty("name", firstNameInput.getText().toString() + " " + miInput.getText().toString()+". "+lastNameInput.getText().toString());
                    user.setProperty("name", firstNameInput.getText().toString() + " "+lastNameInput.getText().toString());
                    user.setProperty("email", emailInput.getText().toString());
                    user.setProperty("password", passInput.getText().toString());
                    user.setProperty("username", usernameInput.getText().toString());
                    user.setProperty("updatedsetup", false);
                    Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            String username = (String) response.getProperty("username");
                            Toast.makeText(CreateAccount.this, "Welcome " +username+", please confirm your email before logging in.", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(CreateAccount.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    Intent i = new Intent(CreateAccount.this, LoginScreen.class);
                    startActivity(i);
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                Intent i = new Intent(this, LoginHelpActivity.class);
                startActivity(i);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    //checks to make sure the password was imputed properly in the two EditTexts
    public boolean confirmPassword(){
        if(passInput.getText().toString().equals(confirmPassInput.getText().toString()) && terms.isChecked()){
            return true;
        }
        else{
            if(!passInput.getText().toString().equals(confirmPassInput.getText().toString())){
                Toast.makeText(CreateAccount.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
            if(!terms.isChecked()){
                Toast.makeText(this, "Please agree to the terms of use.", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }
}

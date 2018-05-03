package com.example.caroline.foodme.UserInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
        setOnCLickListeners();

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
        toolbar= (Toolbar)findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
    }

    public boolean confirmPassword(){
        return (passInput.getText().toString().equals(confirmPassInput.getText().toString())) && terms.isChecked();

    }
}

package com.example.caroline.foodme;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class AccountSettingsActivity extends AppCompatActivity {

    private TextView currentText, newText, passwordText;
    private EditText editSettingText, inputPasswordText;
    private String setting, userID, userUserName, userPassword;
    private Button confirmChangesButton, changePasswordButton;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Backendless.initApp(this, BackendlessSettings.APP_ID, BackendlessSettings.API_KEY);
        Intent i = this.getIntent();
        setting = i.getStringExtra("setting");
        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        userID = sharedPref.getString("userID", "error");
        userUserName = sharedPref.getString("userUserName", "error");
        userPassword = sharedPref.getString("userPassword", "error");
        if(!setting.equals("password")) {
            setContentView(R.layout.activity_account_settings);
            wireWidgets();
            setText();
            setOnClickListeners();
        }
        else{
            setContentView(R.layout.activity_change_password);
            changePassword();
        }
    }

    public void wireWidgets(){
        currentText = findViewById(R.id.account_settings_current_Textview);
        newText = findViewById(R.id.account_settings_new_Textview);
        editSettingText = findViewById(R.id.account_setting_EditText);
        passwordText = findViewById(R.id.account_setting_password_textview);
        inputPasswordText = findViewById(R.id.account_setting_password_EditText);
        confirmChangesButton = findViewById(R.id.account_setting_button);
    }

    public void setText(){
        Log.d("BackendlessUserID", userID);
        Backendless.UserService.isValidLogin( new AsyncCallback<Boolean>()
        {
            @Override
            public void handleResponse( Boolean isValidLogin )
            {
                if( isValidLogin && Backendless.UserService.CurrentUser() == null )
                {
                    if( !userID.equals( "" ) ) {
                        Log.d("UserService", "Current user found, retrieving user");
                        Backendless.UserService.findById(userID, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse( BackendlessUser currentUser )
                            {
                                Log.d("UserService", "User retrieved");
                                Backendless.UserService.setCurrentUser(currentUser);
                                String currentSetting = (String) currentUser.getProperty(setting);
                                currentText.setText("Current " + setting + ": " + currentSetting);

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(AccountSettingsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } );
                    }
                }
                else{
                    Log.d("UserService", "No current user, logging in now");
                    Log.d("UserService", "Username: "+userUserName+"\nPassword: "+userPassword);
                    Backendless.UserService.login(userUserName, userPassword, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            Log.d("UserService", "Logged in");
                            String currentSetting = (String) response.getProperty(setting);
                            currentText.setText("Current " + setting + ": " + currentSetting);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(AccountSettingsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(AccountSettingsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        currentText.setText("Current "+setting+":\nCurrentSetting");
        newText.setText("Input new "+setting+" here:");
        passwordText.setText("Input your current password here:");
    }

    public void setOnClickListeners(){
        confirmChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backendless.UserService.login(userUserName, inputPasswordText.getText().toString(), new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        final String newSetting = editSettingText.getText().toString();
                        if(!newSetting.equals(null)){
                            response.setProperty(setting, newSetting);
                            Backendless.UserService.update(response, new AsyncCallback<BackendlessUser>() {
                                @Override
                                public void handleResponse(BackendlessUser response) {
                                    Log.d("Setting changed", response.getProperty(setting).toString());
                                    if(setting.equals("username")){
                                        SharedPreferences.Editor editor = sharedPref.edit();
                                        editor.remove("userUserName");
                                        editor.putString("userUserName", response.getProperty("username").toString());
                                        editor.commit();
                                    }
                                    Toast.makeText(AccountSettingsActivity.this, setting+" has been changed to "+newSetting, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(AccountSettingsActivity.this, "Please input a new value for your "+setting, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else{
                            Toast.makeText(AccountSettingsActivity.this, "Please input a new value for your "+setting, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(AccountSettingsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void changePassword(){
        changePasswordButton = findViewById(R.id.change_password_button);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backendless.UserService.restorePassword(userUserName, new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        Toast.makeText(AccountSettingsActivity.this, "An email has been sent", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(AccountSettingsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

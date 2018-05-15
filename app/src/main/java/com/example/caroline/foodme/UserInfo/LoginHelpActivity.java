package com.example.caroline.foodme.UserInfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.caroline.foodme.R;

public class LoginHelpActivity extends AppCompatActivity {

    private TextView helpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_help);
        helpText = findViewById(R.id.help_view);
        helpText.setText("Welcome to FoodMe!\n" +
                "FoodMe is a recipe finder and creater app that makes planning your next meal easy.\n" +
                "Use FoodMe to search for recipes, either by name, or by the ingredients you have on hand.\n" +
                "In a pinch, you can also use our very advanced recipe generator to create a custom recipe using your ingredients.\n" +
                "To get started with the app, log in! If you don't have an account yet, click \"New Account\" at the bottom of the login screen.\n "+
                "If you're having trouble logging in, or creating a new account, make sure that your password matches and that you have a valid email.\n" +
                "In case you have forgotten your password, click \"Forgot Password?\" and you will receive an email to reset your password.\n" +
                "Have fun!");
    }
}

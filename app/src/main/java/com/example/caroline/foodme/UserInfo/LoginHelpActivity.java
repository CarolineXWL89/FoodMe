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
        helpText.setText(getString(R.string.help_paragraph));
    }
}

package com.example.caroline.foodme.UserInfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.caroline.foodme.R;

public class TermsOfUseActivity extends AppCompatActivity {

    private TextView termsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_use);
        termsText = findViewById(R.id.terms_view);
        termsText.setText("I will put pineapple on pizza.");
    }
}

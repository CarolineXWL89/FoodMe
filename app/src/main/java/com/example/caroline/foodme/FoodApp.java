package com.example.caroline.foodme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FoodApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i= new Intent(this, HomePageActivity.class);
        startActivity(i);
        //todo create botton nav bar
        //todo create nav menu
        //todo create fragments and make calls
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }
}

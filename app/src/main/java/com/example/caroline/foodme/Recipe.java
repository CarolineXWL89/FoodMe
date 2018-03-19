package com.example.caroline.foodme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Recipe extends AppCompatActivity {

    private ImageView background, image;
    private ImageButton speaker;
    private Button conversions, servingsCyghdzgonversion;
    private TextView name, creator, time, servings, recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        wireWidgets();
    }

    private void wireWidgets() {
        background = (ImageView) findViewById(R.id.background_image);
        image = (ImageView) findViewById(R.id.image_image);
        speaker = (ImageButton) findViewById(R.id.speaker_button);
        //conversions = (Button) findViewById(R.id.);
        //servingsCyghdzgonversion = (Button) findViewById(R.id.);
        name = (TextView) findViewById(R.id.name_textview);
        creator = (TextView) findViewById(R.id.creator__textView);
        time = (TextView) findViewById(R.id.time_textView);
        servings = (TextView) findViewById(R.id.servings_textView);
        recipe = (TextView) findViewById(R.id.recipe_textView);
    }
}

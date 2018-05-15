package com.example.caroline.foodme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeDisplayActivity extends AppCompatActivity {
    private ImageView backgroundImage, mainImage;
    private TextView recipeNameView, servingView, timeNeededView;
    private Button audioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);
        wireWigets();
    }

    private void wireWigets() {
        backgroundImage=findViewById(R.id.background_image)
    }
}

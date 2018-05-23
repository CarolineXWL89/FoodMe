package com.example.caroline.foodme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caroline.foodme.EdamamObjects.RecipeActual;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecipeDisplayActivity extends AppCompatActivity {
    private ImageView backgroundImage, mainImage;
    private TextView recipeNameView, servingView, timeNeededView;
    private ImageButton audioButton;
    private RecipeNative recipeNative;
    private RecipeActual recipeActual;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);
        wireWigets();
        gettingIntents();
        setParts();
    }



    private void wireWigets() {
        backgroundImage=findViewById(R.id.background_image);
        mainImage=findViewById(R.id.image_image);
        recipeNameView = findViewById(R.id.name_textview);
        servingView=findViewById(R.id.servings_textView);
        timeNeededView=findViewById(R.id.time_textView);
        audioButton=findViewById(R.id.speaker_button);

    }

    /**
     * Getting from SearchResultsDisplayer for getting recipes by ingredient

     */
    private void gettingIntents() {
        Intent i = getIntent();
        type=i.getType();
        if (type.equals("RecipeNative")) {
            recipeNative = (RecipeNative) i.getParcelableExtra("recipe_native_show");
        } else {
            recipeActual = (RecipeActual) i.getSerializableExtra("recipe_actual_show");
        }
    }

    private void setParts() {
        if (type.equals("RecipeNative")) {
            //set views
            recipeNameView.setText(recipeNative.getRecipeName());
            //creator.setText(recipeNative.getOwnerId());
            timeNeededView.setText(recipeNative.getTimeNeeded());
            servingView.setText(recipeNative.getServings());
            Picasso.with(RecipeDisplayActivity.this).load("https://www.browneyedbaker.com/wp-content/uploads/2016/05/white-bread-51-600-600x400.jpg").fit().centerCrop().into(backgroundImage);

            Picasso.with(RecipeDisplayActivity.this).load(recipeNative.getImageURL()).fit().centerCrop().into(mainImage);
//            RecipeDisplayActivity.DownloadImageFromURL downloadImageFromURL = new RecipeDisplayActivity.DownloadImageFromURL(mainImage);
//            Bitmap bitmap = downloadImageFromURL.doInBackground(recipeNative.getImageURL());
//            downloadImageFromURL.onPostExecute(bitmap);
            String ingredients = recipeNative.getIngredients();
            recipeNative.setIngredients(ingredients);
            //instructions.setText("Ingredients: \n" + recipeNative.getIngredients() + "\n Directions: " + "\n" + recipeNative.getDirections()); //sets ingredients + directions
//


        } else {
            //set views
            recipeNameView.setText(recipeActual.getLabel());
            //creator.setText(recipeActual.getSource());
            servingView.setText(recipeActual.getYield());
            RecipeDisplayActivity.DownloadImageFromURL downloadImageFromURL = new RecipeDisplayActivity.DownloadImageFromURL(mainImage);
            Bitmap bitmap = downloadImageFromURL.doInBackground(recipeActual.getImage());
            downloadImageFromURL.onPostExecute(bitmap);
            ArrayList<String> ingrs = recipeActual.getIngredientLines();
            //instructions.setText("Ingredients: \n" + recipeActual.setFormattedIngrs(ingrs) + "\nDirections: \n TBD Webview?");
            //TODO display rest in webview?
        }
    }
    /**
     * Inner class extending the AsyncCall (so it doesn't slow down the process) to get an image from a URL.
     */
    private class DownloadImageFromURL extends AsyncTask<String, Void, Bitmap> {
        public DownloadImageFromURL(ImageView imageView) {
            RecipeDisplayActivity.this.mainImage = imageView;
        }

        /**
         * Gets a bitmap from an Image URL
         * @param urls An image URL as a String
         * @return image bitmap
         */
        @Override
        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }

        /**
         * Called after getting the bitmap from doInBackground on the DownloadImageFromURL object
         * @param result bitmap for image gotten from previous method
         */
        protected void onPostExecute(Bitmap result) {
            mainImage.setImageBitmap(result);
        }
    }
}

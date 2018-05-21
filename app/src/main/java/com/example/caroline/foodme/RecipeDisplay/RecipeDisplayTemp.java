package com.example.caroline.foodme.RecipeDisplay;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caroline.foodme.EdamamObjects.RecipeActual;
import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecipeNative;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecipeDisplayTemp extends AppCompatActivity {

    private ImageView imageView, backgroundImage;
    private Button A, B; //TODO convert; A = servings; B = metric/imperial
    private TextView name, creator, time, servings, instructions;
    private ImageButton speakerButton; //TODO reads everything on the page (for disabled/lazy)
    private RecipeNative recipeNative;
    private RecipeActual recipeActual;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display_temp);

        wireWidgets();
        gettingIntents();
        setParts();
//        setTextViews();
        //show image
    }

    private void wireWidgets() {
        imageView = findViewById(R.id.image_image);
        backgroundImage = findViewById(R.id.background_image);
        A = findViewById(R.id.button_a);
        B = findViewById(R.id.button_b);
        name = findViewById(R.id.name_textview);
        creator = findViewById(R.id.creator__textView);
        time = findViewById(R.id.time_textView);
        servings = findViewById(R.id.servings_textView);
        speakerButton = findViewById(R.id.speaker_button);
        instructions = findViewById(R.id.recipe_textView);
    }

    /**
     * Tester (when it actually can run)
     */
    private void setTextViews() {
        name.setText("Apple Pie");
        creator.setText("Johnny Appleseed");
        servings.setText("10,000 pies");
        time.setText("25 years");
    }

    /**
     * Getting from SearchResultsDisplayer for getting recipes by ingredient
     */
    private void gettingIntents() {
        Intent i = getIntent();
        type = i.getType();
        if (type.equals("RecipeNative")) {
            recipeNative = (RecipeNative) i.getParcelableExtra("recipe_native_show");
        } else {
            recipeActual = (RecipeActual) i.getSerializableExtra("recipe_actual_show");
        }
    }

    /**
     * Setting all the text/images, etc... to activity_recipe_display_temp.xml including via URL depending on the type of recipe
     */
    private void setParts() {
        if (type.equals("RecipeNative")) {
            //set views
            name.setText(recipeNative.getRecipeName());
            creator.setText(recipeNative.getOwnerId());
            time.setText(recipeNative.getTimeNeeded());
            servings.setText(recipeNative.getServings());
            DownloadImageFromURL downloadImageFromURL = new DownloadImageFromURL(imageView);
            Bitmap bitmap = downloadImageFromURL.doInBackground(recipeNative.getImageURL());
            downloadImageFromURL.onPostExecute(bitmap);
            String ingredients = recipeNative.getIngredients();
            recipeNative.setIngredients(ingredients);
            instructions.setText("Ingredients: \n" + recipeNative.getIngredients() + "\n Directions: " + "\n" + recipeNative.getDirections()); //sets ingredients + directions
            DownloadImageFromURL downloadImageFromURLBackground = new DownloadImageFromURL(backgroundImage); //temp set
            Bitmap bitmapBackground = downloadImageFromURLBackground.doInBackground("https://en.wikipedia.org/wiki/Forest#/media/File:Brazilian_amazon_rainforest.jpg");
            downloadImageFromURLBackground.onPostExecute(bitmapBackground);

        } else {
            //set views
            name.setText(recipeActual.getLabel());
            creator.setText(recipeActual.getSource());
            servings.setText(recipeActual.getYield());
            DownloadImageFromURL downloadImageFromURL = new DownloadImageFromURL(imageView);
            Bitmap bitmap = downloadImageFromURL.doInBackground(recipeActual.getImage());
            downloadImageFromURL.onPostExecute(bitmap);
            ArrayList<String> ingrs = recipeActual.getIngredientLines();
            instructions.setText("Ingredients: \n" + recipeActual.setFormattedIngrs(ingrs) + "\nDirections: \n TBD Webview?");
            //TODO display rest in webview?
        }
    }

    /**
     * Inner class extending the AsyncCall (so it doesn't slow down the process) to get an image from a URL.
     */
    private class DownloadImageFromURL extends AsyncTask<String, Void, Bitmap> {
        public DownloadImageFromURL(ImageView imageView) {
            RecipeDisplayTemp.this.imageView = imageView;
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
            imageView.setImageBitmap(result);
        }
    }
    /* SAMPLE CODE
     *show The Image in a ImageView
     *new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
     *      .execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");

     *public void onClick(View v) {
     *startActivity(new Intent(this, IndexActivity.class));
     *finish();
     *}
     */
}


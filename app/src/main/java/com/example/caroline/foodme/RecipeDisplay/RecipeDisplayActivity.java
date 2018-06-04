package com.example.caroline.foodme.RecipeDisplay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.caroline.foodme.BackendlessSettings;
import com.example.caroline.foodme.EdamamObjects.RecipeActual;
import com.example.caroline.foodme.FavoritesFragment.Favorites;
import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecipeNative;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecipeDisplayActivity extends AppCompatActivity {
    private ImageView backgroundImage, mainImage;
    private TextView recipeNameView, servingView, timeNeededView;
    private ImageButton audioButton, favoriteButton;
    private RecipeNative recipeNative;
    private RecipeActual recipeActual;
    private String type;
    private SharedPreferences sharedPref;
    private boolean favorited; //TODO: set the favoriteButton so that it removes the object
    // from Backendless's list of favorites if the button is clicked when this variable is true.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);
        sharedPref = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Backendless.initApp(this, BackendlessSettings.APP_ID, BackendlessSettings.API_KEY);
        wireWigets();
        setFavorited();
        setOnClickListeners();
        gettingIntents();
        setParts();
    }

    public void setFavorited(){
        Backendless.UserService.login(sharedPref.getString("userUserName", "null"), sharedPref.getString("userPassword", "null"), new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                String favorites = response.getProperty("favorites").toString();
                ArrayList<String> recipes = arrayParser(favorites);
                String searchID = "";
                if(type.equals("RecipeNative")){
                    searchID = recipeNative.getObjectId();
                }
                else{
                    searchID = recipeActual.getUri();
                }
                if(recipes.contains(searchID)){
                    favorited = true;
                    favoriteButton.setImageResource(R.drawable.ic_filled_star);
                }
                else{
                    favorited = false;
                    favoriteButton.setImageResource(R.drawable.ic_star_border_black_24dp);
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    public ArrayList<String> arrayParser(String string){
        ArrayList<String> array = new ArrayList<>();
        string = string.substring(1, string.length()-1);
        while(string.indexOf(", ")!=-1){
            array.add(string.substring(0, string.indexOf(", ")));
            string = string.substring(string.indexOf(", ")+2);
        }
        array.add(string);
        return array;
    }

    private void wireWigets() {
        backgroundImage=findViewById(R.id.background_image);
        mainImage=findViewById(R.id.image_image);
        recipeNameView=findViewById(R.id.name_textview);
        servingView=findViewById(R.id.servings_textView);
        timeNeededView=findViewById(R.id.time_textView);
        audioButton=findViewById(R.id.speaker_button);
        favoriteButton = findViewById(R.id.favorite_button);
    }

    public void setOnClickListeners(){
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favorited){
                    favorited = false;
                    favoriteButton.setImageResource(R.drawable.ic_star_border_black_24dp);
                    Backendless.UserService.login(sharedPref.getString("userUserName", "null"), sharedPref.getString("userPassword", "null"), new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            String favorites = response.getProperty("favorites").toString();
                            if(type.equals("RecipeNative")){
                                String whereClause = "backendlessID = '"+recipeNative.getObjectId()+"'";
                                Log.d("whereClause", whereClause);
                                DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                                queryBuilder.setWhereClause(whereClause);
                                Backendless.Data.of(Favorites.class).find(queryBuilder, new AsyncCallback<List<Favorites>>() {
                                    @Override
                                    public void handleResponse(List<Favorites> response) {
                                        int oldFrequency = response.get(0).getFrequency();
                                        response.get(0).setFrequency(oldFrequency-1);
                                        response.get(0).setBackendless(true);
                                        Backendless.Persistence.of(Favorites.class).save(response.get(0), new AsyncCallback<Favorites>() {
                                            @Override
                                            public void handleResponse(Favorites response) {

                                            }

                                            @Override
                                            public void handleFault(BackendlessFault fault) {
                                                Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                                Log.d("(-) saving frequency", fault.getMessage());
                                            }
                                        });
                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {
                                        Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("(-) searching", fault.getMessage());
                                    }
                                });
                                ArrayList<String> recipes = arrayParser(favorites);
                                Log.d("favorites", recipes.toString());
                                recipes.remove(recipeNative.getObjectId());
                                Log.d("updated favorites", recipes.toString());
                                response.setProperty("favorites", recipes.toString());
                                Backendless.UserService.update(response, new AsyncCallback<BackendlessUser>() {
                                    @Override
                                    public void handleResponse(BackendlessUser response) {

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {
                                        Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("(-) saving arraylist", fault.getMessage());
                                    }
                                });

                            }
                            else{
                                String whereClause = "edamamID = '"+recipeActual.getUri()+"'";
                                DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                                queryBuilder.setWhereClause(whereClause);
                                Backendless.Data.of(Favorites.class).find(queryBuilder, new AsyncCallback<List<Favorites>>() {
                                    @Override
                                    public void handleResponse(List<Favorites> response) {
                                        int oldFrequency = response.get(0).getFrequency();
                                        response.get(0).setFrequency(oldFrequency-1);
                                        response.get(0).setBackendless(false);
                                        Backendless.Persistence.of(Favorites.class).save(response.get(0), new AsyncCallback<Favorites>() {
                                            @Override
                                            public void handleResponse(Favorites response) {

                                            }

                                            @Override
                                            public void handleFault(BackendlessFault fault) {
                                                Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                                Log.d("(-) saving frequency", fault.getMessage());
                                            }
                                        });
                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {
                                        Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("(-) searching", fault.getMessage());
                                    }
                                });
                                ArrayList<String> recipes = arrayParser(favorites);
                                Log.d("favorites", recipes.toString());
                                recipes.add(recipeActual.getUri());
                                Log.d("updated favorites", recipes.toString());
                                response.setProperty("favorites", recipes.toString());
                                Backendless.UserService.update(response, new AsyncCallback<BackendlessUser>() {
                                    @Override
                                    public void handleResponse(BackendlessUser response) {

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {
                                        Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("(-) saving arraylist", fault.getMessage());
                                    }
                                });
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.d("(-) login", fault.getMessage());
                        }
                    });
                }
                else{
                    favorited = true;
                    favoriteButton.setImageResource(R.drawable.ic_filled_star);
                    Log.d("username", sharedPref.getString("userUserName", "null"));
                    Log.d("password", sharedPref.getString("userPassword", "null"));
                    Backendless.UserService.login(sharedPref.getString("userUserName", "null"), sharedPref.getString("userPassword", "null"), new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            if(type.equals("RecipeNative")){
                                String whereClause = "backendlessID = '"+recipeNative.getObjectId()+"'";
                                DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                                queryBuilder.setWhereClause(whereClause);
                                Backendless.Data.of(Favorites.class).find(queryBuilder, new AsyncCallback<List<Favorites>>() {
                                    @Override
                                    public void handleResponse(List<Favorites> response) {
                                        if(response.size() == 0){
                                            Favorites newFav = new Favorites();
                                            newFav.setBackendlessID(recipeNative.getObjectId());
                                            newFav.setFrequency(1);
                                            newFav.setBackendless(true);
                                            Backendless.Data.of(Favorites.class).save(newFav, new AsyncCallback<Favorites>() {
                                                @Override
                                                public void handleResponse(Favorites response) {
                                                    Log.d("New favorite", "success");
                                                }

                                                @Override
                                                public void handleFault(BackendlessFault fault) {
                                                    Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                                    Log.d("(+) creating favorite", fault.getMessage());
                                                }
                                            });
                                        }
                                        else{
                                            int oldFrequency = (int) response.get(0).getFrequency();
                                            response.get(0).setFrequency(oldFrequency+1);
                                            response.get(0).setBackendless(true);
                                            Backendless.Persistence.of(Favorites.class).save(response.get(0), new AsyncCallback<Favorites>() {
                                                @Override
                                                public void handleResponse(Favorites response) {

                                                }

                                                @Override
                                                public void handleFault(BackendlessFault fault) {
                                                    Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                                    Log.d("(+) saving frequency", fault.getMessage());
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {
                                        Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("(+) searching", fault.getMessage());
                                    }
                                });
                                if(response.getProperty("favorites") == null || response.getProperty("favorites").toString().length()==0){
                                    ArrayList<String> recipes = new ArrayList<>();
                                    recipes.add(recipeNative.getObjectId());
                                    response.setProperty("favorites", recipes.toString());
                                    Backendless.Data.save(response, new AsyncCallback<BackendlessUser>() {
                                        @Override
                                        public void handleResponse(BackendlessUser response) {

                                        }

                                        @Override
                                        public void handleFault(BackendlessFault fault) {
                                            Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                            Log.d("(+) creating arraylist", fault.getMessage());
                                        }
                                    });
                                }
                                else{
                                    String favorites = response.getProperty("favorites").toString();
                                    ArrayList<String> recipes = arrayParser(favorites);
                                    Log.d("favorites", recipes.toString());
                                    recipes.add(recipeNative.getObjectId());
                                    Log.d("updated favorites", recipes.toString());
                                    response.setProperty("favorites", recipes.toString());
                                    Backendless.Persistence.save(response, new AsyncCallback<BackendlessUser>() {
                                        @Override
                                        public void handleResponse(BackendlessUser response) {

                                        }

                                        @Override
                                        public void handleFault(BackendlessFault fault) {
                                            Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                            Log.d("(+) saving arraylist", fault.getMessage());
                                        }
                                    });

                                }
                            }
                            else{
                                String whereClause = "edamamID = '"+recipeActual.getUri()+"'";
                                DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                                queryBuilder.setWhereClause(whereClause);
                                Backendless.Data.of(Favorites.class).find(queryBuilder, new AsyncCallback<List<Favorites>>() {
                                    @Override
                                    public void handleResponse(List<Favorites> response) {
                                        if(response.size() == 0){
                                            Favorites newFav = new Favorites();
                                            newFav.setEdamamID(recipeActual.getUri());
                                            newFav.setFrequency(1);
                                            newFav.setBackendless(false);
                                            Backendless.Data.of(Favorites.class).save(newFav, new AsyncCallback<Favorites>() {
                                                @Override
                                                public void handleResponse(Favorites response) {
                                                    Log.d("New favorite", "success");
                                                }

                                                @Override
                                                public void handleFault(BackendlessFault fault) {
                                                    Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                                    Log.d("(+) creating favorite", fault.getMessage());
                                                }
                                            });
                                        }
                                        else{
                                            int oldFrequency = (int) response.get(0).getFrequency();
                                            response.get(0).setFrequency(oldFrequency+1);
                                            response.get(0).setBackendless(false);
                                            Backendless.Persistence.of(Favorites.class).save(response.get(0), new AsyncCallback<Favorites>() {
                                                @Override
                                                public void handleResponse(Favorites response) {

                                                }

                                                @Override
                                                public void handleFault(BackendlessFault fault) {
                                                    Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                                    Log.d("(+) saving frequency", fault.getMessage());
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {
                                        Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("(+) searching", fault.getMessage());
                                    }
                                });
                                if(response.getProperty("favorites") == null || response.getProperty("favorites").toString().length()==0){
                                    ArrayList<String> recipes = new ArrayList<>();
                                    recipes.add(recipeActual.getUri());
                                    response.setProperty("favorites", recipes.toString());
                                    Backendless.UserService.update(response, new AsyncCallback<BackendlessUser>() {
                                        @Override
                                        public void handleResponse(BackendlessUser response) {

                                        }

                                        @Override
                                        public void handleFault(BackendlessFault fault) {
                                            Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                            Log.d("(+) creating arraylist", fault.getMessage());
                                        }
                                    });
                                }
                                else{
                                    String favorites = response.getProperty("favorites").toString();
                                    ArrayList<String> recipes = arrayParser(favorites);
                                    recipes.add(recipeActual.getUri());
                                    response.setProperty("favorites", recipes.toString());
                                    Backendless.UserService.update(response, new AsyncCallback<BackendlessUser>() {
                                        @Override
                                        public void handleResponse(BackendlessUser response) {

                                        }

                                        @Override
                                        public void handleFault(BackendlessFault fault) {
                                            Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                            Log.d("(+) saving arraylist", fault.getMessage());
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(RecipeDisplayActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("(+) login", fault.getMessage());
                        }
                    });
                }

            }
        });
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

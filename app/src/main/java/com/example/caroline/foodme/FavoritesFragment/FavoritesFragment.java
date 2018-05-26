package com.example.caroline.foodme.FavoritesFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.caroline.foodme.API_Interfaces.DataMuseNutritionIngrParser;
import com.example.caroline.foodme.API_Interfaces.DataMuseNutritionSearch;
import com.example.caroline.foodme.API_Interfaces.DataMuseRecipe;
import com.example.caroline.foodme.EdamamNutritionKeys;
import com.example.caroline.foodme.EdamamObjects.Hint;
import com.example.caroline.foodme.EdamamObjects.RecipeActual;
import com.example.caroline.foodme.EdamamRecipeKeys;
import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecipeDisplay.RecipeDisplayActivity;
import com.example.caroline.foodme.RecipeNative;
import com.example.caroline.foodme.UserInfo.LoginScreen;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoritesFragment extends Fragment {

    CarouselView carouselView;
    private View rootview;
    private RecyclerView favoritesRecyclerview;
    private ArrayList<Favorites> userFavorites;
    private ArrayList<DisplayerRecipe> userFavoritesDisplayer;
    private ArrayList<DisplayerRecipe> generalFavorites;
    private RecyclerView.LayoutManager layoutManager;
    private FavoritesDisplayAdapter favoritesDisplayAdapter;
    private ProgressBar spinningPinWheelOfDeath;

    public static final String TAG = "fragments";
    public FavoritesFragment() {
        // Required empty public constructorr
        //todo save general favorites and refresh every time Home paga activity is restarted not when favorites is restarted should fix slow problem
        //need ot search user favorites everytime
        //todo need to test edamam code
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_favorites, container, false);
        spinningPinWheelOfDeath = rootview.findViewById(R.id.pinwheelOfDeath);
        spinningPinWheelOfDeath.setIndeterminate(true);
        getUserFavorites();
        return rootview;
    }

    private void getUserFavorites() {
        userFavorites = new ArrayList<>();
        BackendlessUser backendlessUser = Backendless.UserService.CurrentUser();
        if (backendlessUser != null) {
            String favoritesString = (String) backendlessUser.getProperty("favorites");
            if (favoritesString != null) {
                final String[] favoritesArray = favoritesString.split(" ");
                for (final String s : favoritesArray) {
                    Backendless.Persistence.of(Favorites.class).findById(s, new AsyncCallback<Favorites>() {
                        @Override
                        public void handleResponse(Favorites response) {
                            userFavorites.add(response);
                            if (response.getBackendless()) { //backendless
                                if (userFavorites.size() == favoritesArray.length) {
                                    fillUserDisplayer();
                                }
                            } else { //not backendless
                                if (userFavorites.size() == favoritesArray.length) {
                                    fillUserDisplayer();
                                }
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "handleFault: "+ fault.getMessage());
                        }
                    });
                }
            } else {
                userFavoritesDisplayer = new ArrayList<>();
                userFavoritesDisplayer.add(new DisplayerRecipe("empty", "", false, "Need to add recipe"));
            }
        } else {
            Snackbar snackbar = Snackbar.make(rootview, "No user found please log in", Snackbar.LENGTH_LONG);
            snackbar.setAction("LOGIN", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(), LoginScreen.class);
                    startActivity(i);
                }
            });
            snackbar.show();
        }
    }

    private void fillUserDisplayer() {
        userFavoritesDisplayer = new ArrayList<>();
        Favorites lastRecipe = userFavorites.get(userFavorites.size() - 1);
        String last = "";
        if(lastRecipe.getBackendless()){
            last = lastRecipe.getBackendlessID();
        } else {
            last = lastRecipe.getEdamamID();
        }
        final String finalLast = last;
        for(Favorites f: userFavorites){
            if(f.getBackendless()){ //if from backendless gets recipe
                final String key = f.getBackendlessID();
                Backendless.Persistence.of(RecipeNative.class).findById(f.getBackendlessID(), new AsyncCallback<RecipeNative>() {
                    @Override
                    public void handleResponse(RecipeNative response) {
                        DisplayerRecipe displayerRecipe = new DisplayerRecipe(response.getObjectId(), response.getImageURL(), true, response.getRecipeName());
                        userFavoritesDisplayer.add(displayerRecipe);
                        if(key.equals(finalLast)){ //makes sure we wait til last async call occurs
                            getOverallFavorites();
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getContext(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                        userFavoritesDisplayer.add(new DisplayerRecipe("",
                                "http://www.isic.es/wp-content/plugins/orchitech-dm/resources/alive-dm/img/empty-image.png",
                                false, "No favorites found.\nPlease check internet connection"));
                        getOverallFavorites();
                    }
                });
            } else {
                //todo edamam serach by id thing make sure async doesnt call until end
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(DataMuseRecipe.baseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                DataMuseRecipe api = retrofit.create(DataMuseRecipe.class);

                final String key = f.getEdamamID();
                Call<RecipeActual> call = api.getRecipeFromURI(f.getEdamamID(), EdamamRecipeKeys.APP_ID_RECIPE , EdamamRecipeKeys.APP_KEY_RECIPE);
                call.enqueue(new Callback<RecipeActual>() {
                    @Override
                    public void onResponse(Call<RecipeActual> call, Response<RecipeActual> response) {
                        RecipeActual recipe = response.body();
                        DisplayerRecipe r = new DisplayerRecipe(recipe.getWorkingURI(recipe.getUri()), recipe.getImage(), false, recipe.getLabel());
                        generalFavorites.add(r);
                        if(key.equals(finalLast)){ //makes sure we wait til last async call occurs
                            wireWidgets();
                        }
                    }

                    @Override
                    public void onFailure(Call<RecipeActual> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "handleFault: "+t.getMessage());
                        DisplayerRecipe r = new DisplayerRecipe();
                        r.setImageURL("http://www.isic.es/wp-content/plugins/orchitech-dm/resources/alive-dm/img/empty-image.png");
                        r.setName("No favorites found.\nPlease check internet connection");
                        userFavoritesDisplayer.add(r);
                        if(key.equals(finalLast)){ //makes sure we wait til last async call occurs
                            wireWidgets();
                        }
                    }
                });
                RecipeNative r = new RecipeNative();
                //imageURLS.add(r.getImageURL());
                //titles.add(r.getRecipeName());
            }
        }
    }


    private void wireWidgets() {
        //wires recycler view and adds adapters
        spinningPinWheelOfDeath.setVisibility(View.GONE);

        favoritesRecyclerview = rootview.findViewById(R.id.favoritesRecyclerView);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        favoritesRecyclerview.setLayoutManager(layoutManager);
        favoritesRecyclerview.setItemAnimator(new DefaultItemAnimator());
        favoritesDisplayAdapter = new FavoritesDisplayAdapter(userFavoritesDisplayer, getContext());
        favoritesRecyclerview.setAdapter(favoritesDisplayAdapter);
        registerForContextMenu(favoritesRecyclerview);

        //checks if no favorites removes placeholder item
        if(userFavoritesDisplayer.get(0).getId().equals("empty")){
            userFavoritesDisplayer.remove(0);
            Toast.makeText(getActivity(), "No favorites currently.\nTo add favorites heart recipes you like", Toast.LENGTH_LONG).show();
        }

        ViewListener viewListener = new ViewListener() {
            @Override
            public View setViewForPosition(final int position) {
                View customView = getLayoutInflater().inflate(R.layout.carousel_item, null);
                ImageView imageView = customView.findViewById(R.id.carousel_image_view);
                TextView textView = customView.findViewById(R.id.carousel_text_view);
                Picasso.with(getContext()).load(generalFavorites.get(position).getImageURL()).fit().centerCrop().into(imageView);
                textView.setText(generalFavorites.get(position).getName());
                customView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DisplayerRecipe r = generalFavorites.get(position);
                        if(r.isBackendless()){
                            Backendless.Persistence.of(RecipeNative.class).findById(r.getId(), new AsyncCallback<RecipeNative>() {
                                @Override
                                public void handleResponse(RecipeNative response) {
                                    Intent i = new Intent(getActivity(), RecipeDisplayActivity.class);
                                    i.setType("RecipeNative");
                                    i.putExtra("recipe_native_show", response);
                                    startActivity(i);
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "handleFault: "+ fault.getMessage());
                                }
                            });
                        }
                    }
                });
                return customView;
            }
        };

        carouselView = (CarouselView) rootview.findViewById(R.id.carouselView);
        carouselView.setViewListener(viewListener);
        carouselView.setPageCount(generalFavorites.size());
        carouselView.reSetSlideInterval(5000);
    }

    public void setImages(ArrayList<KeyValueFavorite> favorites){
        generalFavorites = new ArrayList<>();
        if(favorites.get(0) == null) {
            generalFavorites.get(0).setImageURL("http://www.isic.es/wp-content/plugins/orchitech-dm/resources/alive-dm/img/empty-image.png");
            generalFavorites.get(0).setName("No favorites found.\nPlease check internet connection");
            wireWidgets();
        } else{
            for(final KeyValueFavorite f: favorites){
                final String last = favorites.get(favorites.size() - 1).getKey(); //gets last string so we knoe when to call wire widgets
                if(f.isBackendless()){ //if from backendless gets recipe
                    final String key = f.getKey();
                    Backendless.Persistence.of(RecipeNative.class).findById(f.getKey(), new AsyncCallback<RecipeNative>() {
                        @Override
                        public void handleResponse(RecipeNative response) {
                            DisplayerRecipe r = new DisplayerRecipe(response.getObjectId(), response.getImageURL(), true, response.getRecipeName());
                            generalFavorites.add(r);
                            if(key.equals(last)){ //makes sure we wait til last async call occurs
                                wireWidgets();
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(getContext(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "handleFault: "+fault.getMessage());
                            generalFavorites.get(0).setImageURL("http://www.isic.es/wp-content/plugins/orchitech-dm/resources/alive-dm/img/empty-image.png");
                            generalFavorites.get(0).setName("No favorites found.\nPlease check internet connection");
                            wireWidgets();
                        }
                    });
                } else {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(DataMuseRecipe.baseURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    DataMuseRecipe api = retrofit.create(DataMuseRecipe.class);

                    final String key = f.getKey();
                    Call<RecipeActual> call = api.getRecipeFromURI(f.getKey(), EdamamRecipeKeys.APP_ID_RECIPE , EdamamRecipeKeys.APP_KEY_RECIPE);
                    call.enqueue(new Callback<RecipeActual>() {
                        @Override
                        public void onResponse(Call<RecipeActual> call, Response<RecipeActual> response) {
                            RecipeActual recipe = response.body();
                            DisplayerRecipe r = new DisplayerRecipe(recipe.getWorkingURI(recipe.getUri()), recipe.getImage(), false, recipe.getLabel());
                            generalFavorites.add(r);
                            if(key.equals(last)){ //makes sure we wait til last async call occurs
                                wireWidgets();
                            }
                        }

                        @Override
                        public void onFailure(Call<RecipeActual> call, Throwable t) {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "handleFault: "+t.getMessage());
                            DisplayerRecipe r = new DisplayerRecipe();
                            r.setImageURL("http://www.isic.es/wp-content/plugins/orchitech-dm/resources/alive-dm/img/empty-image.png");
                            r.setName("No favorites found.\nPlease check internet connection");
                            generalFavorites.add(r);
                            if(key.equals(last)){ //makes sure we wait til last async call occurs
                                wireWidgets();
                            }
                        }
                    });
                }
            }
        }
    }

    private void getOverallFavorites() {
        final ArrayList<KeyValueFavorite> favoritesList = new ArrayList<>();

        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setSortBy("frequency DESC");
        queryBuilder.setPageSize(5);
        Backendless.Data.of(Favorites.class).find(queryBuilder, new AsyncCallback<List<Favorites>>(){
            @Override
            public void handleResponse( List<Favorites> response ) {
                for(Favorites f: response){
                    if(f.getBackendless()){ //backenless object
                        favoritesList.add(new KeyValueFavorite(f.getBackendlessID(), f.getFrequency(), true));
                    } else { //edmama object
                        favoritesList.add(new KeyValueFavorite(f.getEdamamID(), f.getFrequency(), false));
                    }
                }
                Log.d(TAG, "handleResponse: "+favoritesList.size());
                setImages(favoritesList);
            }

            @Override
            public void handleFault( BackendlessFault fault ) {
                Toast.makeText(getActivity(), fault.getCode(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

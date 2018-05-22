package com.example.caroline.foodme.FavoritesFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecipeNative;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FavoritesFragment extends Fragment {

    CarouselView carouselView;
    private ArrayList<String> imageURLS, titles;
    private View rootview;
    private RecyclerView favoritesRecyclerview;
    private ArrayList<Favorites> userFavorites;
    private ArrayList<DisplayerRecipe> userFavoritesDisplayer;
    private RecyclerView.LayoutManager layoutManager;
    private FavoritesDisplayAdapter favoritesDisplayAdapter;

    public static final String TAG = "fragments";
    public FavoritesFragment() {
        // Required empty public constructorr
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
        setFavorites();
        return rootview;
    }

    private void setFavorites() {
        userFavorites = new ArrayList<>();
        BackendlessUser backendlessUser = Backendless.UserService.CurrentUser();
        if (backendlessUser != null) {
            String favoritesString = (String) backendlessUser.getProperty("favorites");
            if (favoritesString != null) {
                final String[] favoritesArray = favoritesString.split(" ");
                //final String last = favoritesArray[favoritesArray.length - 1];
                for (final String s : favoritesArray) {
                    Backendless.Persistence.of(Favorites.class).findById(s, new AsyncCallback<Favorites>() {
                        @Override
                        public void handleResponse(Favorites response) {
                            userFavorites.add(response);
                            Log.d(TAG, "handleResponse: "+response.getBackendlessID());
                            if (response.getBackendless()) { //backendless
                                if (userFavorites.size() == favoritesArray.length) {
                                    Log.d(TAG, "handleResponse: equals last");
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
                            Log.d(TAG, "handleFault: "+fault.toString());
                            Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "handleFault: "+ fault.getMessage());
                        }
                    });
                }
            } else {
                //todo make empty it that says go favorite stuff
            }
        } else {
            Toast.makeText(getActivity(), "Need to log in", Toast.LENGTH_SHORT).show();
        }
    }

    private void fillUserDisplayer() {
        Log.d(TAG, "fillUserDisplayer: started");
        userFavoritesDisplayer = new ArrayList<>();
        final String last = userFavorites.get(userFavorites.size() - 1).getBackendlessID(); //fix for edama
        Log.d(TAG, "fillUserDisplayer: "+userFavorites.size());
        for(Favorites f: userFavorites){
            if(f.getBackendless()){ //if from backendless gets recipe
                final String key = f.getBackendlessID();
                Backendless.Persistence.of(RecipeNative.class).findById(f.getBackendlessID(), new AsyncCallback<RecipeNative>() {
                    @Override
                    public void handleResponse(RecipeNative response) {
                        DisplayerRecipe displayerRecipe = new DisplayerRecipe(response.getObjectId(), response.getImageURL(), true, response.getRecipeName());
                        userFavoritesDisplayer.add(displayerRecipe);
                        if(key.equals(last)){ //makes sure we wait til last async call occurs
                            getFavorites();
                            Log.d(TAG, "handleResponse: moving from fill user display");
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getContext(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                        //todo check what happens if fault
                    }
                });
            } else {
                //todo edamam serach by id thing make sure async doesnt call until end
                RecipeNative r = new RecipeNative();
                imageURLS.add(r.getImageURL());
                titles.add(r.getRecipeName());
            }
        }
    }


    private void wireWidgets() {
        //wires recycler view and adds adapters
        Log.d(TAG, "wireWidgets: "+userFavoritesDisplayer.size());
        for(DisplayerRecipe dr: userFavoritesDisplayer){
            Log.d(TAG, "wireWidgets: "+dr.getName());
        }
        favoritesRecyclerview = rootview.findViewById(R.id.favoritesRecyclerView);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        favoritesRecyclerview.setLayoutManager(layoutManager);
        favoritesRecyclerview.setItemAnimator(new DefaultItemAnimator());
        favoritesDisplayAdapter = new FavoritesDisplayAdapter(userFavoritesDisplayer, getContext());
        favoritesRecyclerview.setAdapter(favoritesDisplayAdapter);
        registerForContextMenu(favoritesRecyclerview);

        ViewListener viewListener = new ViewListener() {
            @Override
            public View setViewForPosition(int position) {
                View customView = getLayoutInflater().inflate(R.layout.carousel_item, null);
                ImageView imageView = customView.findViewById(R.id.carousel_image_view);
                TextView textView = customView.findViewById(R.id.carousel_text_view);
                Picasso.with(getContext()).load(imageURLS.get(position)).fit().centerCrop().into(imageView);
                textView.setText(titles.get(position));
                //todo on click load recipe
                return customView;
            }
        };

        carouselView = (CarouselView) rootview.findViewById(R.id.carouselView);
        carouselView.setViewListener(viewListener);
        carouselView.setPageCount(imageURLS.size());
        carouselView.reSetSlideInterval(5000);
    }

    public void setImages(KeyValueFavorite[] favorites){
        imageURLS = new ArrayList<>();
        titles = new ArrayList<>();
        for(final KeyValueFavorite f: favorites){
            final String last = favorites[favorites.length - 1].getKey(); //gets last string so we knoe when to call wire widgets
            if(f.isBackendless()){ //if from backendless gets recipe
                final String key = f.getKey(); //todo use same emthod in edamam
                Backendless.Persistence.of(RecipeNative.class).findById(f.getKey(), new AsyncCallback<RecipeNative>() {
                    @Override
                    public void handleResponse(RecipeNative response) {
                        imageURLS.add(response.getImageURL());
                        titles.add(response.getRecipeName());
                        if(key.equals(last)){ //makes sure we wait til last async call occurs
                            wireWidgets();
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getContext(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                        //todo check what happens if fault
                    }
                });
            } else {
                //todo edamam serach by id thing make sure async doesnt call until end
                RecipeNative r = new RecipeNative();
                imageURLS.add(r.getImageURL());
                titles.add(r.getRecipeName());
            }
        }
    }

    private void getFavorites() {
        final KeyValueFavorite[] favorites = new KeyValueFavorite[3];
        final ArrayList<KeyValueFavorite> favoritesList = new ArrayList<>();

        Backendless.Persistence.of(Favorites.class).find(new AsyncCallback<List<Favorites>>() {
            @Override
            public void handleResponse(List<Favorites> response) {
                for(Favorites f : response){
                    if(f.getBackendless()){ //backenless object
                        favoritesList.add(new KeyValueFavorite(f.getBackendlessID(), f.getFrequency(), true));
                    } else { //edmama object
                        favoritesList.add(new KeyValueFavorite(f.getEdamamID(), f.getFrequency(), false));
                    }
                }
                Collections.sort(favoritesList);
                int len = 5; //makes sure enough favorites
                if(favoritesList.size() < len){
                    len = favoritesList.size();
                }
                //adds top five to array
                for(int i = 0; i < len; i++){
                    favorites[i] = favoritesList.get(i);
                }
                setImages(favorites);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

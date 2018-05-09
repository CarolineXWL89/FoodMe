package com.example.caroline.foodme.FavoritesFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecipeNative;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    CarouselView carouselView;
    private ArrayList<String> imageURLS;
    private ArrayList<String> titles;
    private View rootview;
    private RecyclerView favoritesRecyclerview;
    private ArrayList<RecipeNative> favoritesList;
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
        favoritesList = new ArrayList<>();
        //todo work on bckeneless to save users favorites, get thema nd load into favoriteslist
        RecipeNative r = new RecipeNative();
        r.setRecipeName("I love pie");
        r.setImageURL("https://static-cdn.jtvnw.net/jtv_user_pictures/e91a3dcf-c15a-441a-b369-996922364cdc-profile_image-300x300.png");
        favoritesList.add(r);

        RecipeNative r2 = new RecipeNative();
        r2.setRecipeName("I love apples");
        r2.setImageURL("https://vignette.wikia.nocookie.net/phobia/images/1/1b/Purple.jpg/revision/latest?cb=20161109231115");
        favoritesList.add(r2);

        RecipeNative r3 = new RecipeNative();
        r3.setRecipeName("I love water");
        r3.setImageURL("http://www.solidbackgrounds.com/images/1920x1080/1920x1080-yellow-solid-color-background.jpg");
        favoritesList.add(r3);

        setImages();
    }

    private void wireWidgets() {
        //wires recycler view and adds adapters
        favoritesRecyclerview = rootview.findViewById(R.id.favoritesRecyclerView);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        favoritesRecyclerview.setLayoutManager(layoutManager);
        favoritesRecyclerview.setItemAnimator(new DefaultItemAnimator());
        favoritesDisplayAdapter = new FavoritesDisplayAdapter(favoritesList, getContext());
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
                return customView;
            }
        };

        carouselView = (CarouselView) rootview.findViewById(R.id.carouselView);
        carouselView.setPageCount(titles.size());
        carouselView.setViewListener(viewListener);
        carouselView.reSetSlideInterval(5000);
    }

    public void setImages(){
        //todo use top four favorites with your dietary needs aroudn the world (aka our user database)
        imageURLS = new ArrayList<>();
        titles = new ArrayList<>();
        imageURLS.add("https://www.nutstop.com/wp-content/uploads/2015/07/Cashews-Raw-240-Nutstop.jpg");
        imageURLS.add("https://images.eatthismuch.com/site_media/img/2632_ldementhon_b3a80d6c-1144-4a6a-9b5f-db86bda38fc6.png");
        imageURLS.add("https://cimg2.ibsrv.net/cimg/www.fitday.com/693x350_85-1/970/dark-20chocolate-105970.jpg");
        titles.add("Cashews");
        titles.add("Almonds");
        titles.add("Chocolate");
        wireWidgets();
    }


}

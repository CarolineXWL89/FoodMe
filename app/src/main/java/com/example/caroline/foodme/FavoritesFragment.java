package com.example.caroline.foodme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    CarouselView carouselView;
    private ArrayList<Integer> images;
    private ArrayList<String> titles;


    public static final String TAG = "fragments";
    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(images.size());
        carouselView.setViewListener(viewListener);


        return view;
    }
//

    public void setImages(){
        images = new ArrayList<Integer>();
        titles = new ArrayList<String>();
    }

    ViewListener viewListener = new ViewListener() {

        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.carousel_item, null);
            ImageView imageView = customView.findViewById(R.id.carousel_image_view);
            TextView textView = customView.findViewById(R.id.carousel_text_view);
            imageView.setImageResource(images.get(position));
            textView.setText(titles.get(position));
            return customView;
        }
    };
}

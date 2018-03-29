package com.example.caroline.foodme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class CarouselViewActivity extends AppCompatActivity {
    //creates a carouselView: a slidshow of images which can be scrolled through horizontally.

    CarouselView carouselView;
    private ArrayList<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel_view);

        setImages();

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(images.size());
        carouselView.setImageListener(imageListener);
    }

    //Add images to the arraylist here, these images will scroll
    public void setImages(){
        images = new ArrayList<Integer>();
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(images.get(position));
        }
    };

}

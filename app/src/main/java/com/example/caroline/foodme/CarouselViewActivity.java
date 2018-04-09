package com.example.caroline.foodme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

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
        carouselView.setViewListener(viewListener);
    }

    //Add images to the arraylist here, these images will scroll
    public void setImages(){
        images = new ArrayList<Integer>();
    }

    ViewListener viewListener = new ViewListener() {

        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.carousel_item, null);
            ImageView imageView = customView.findViewById(R.id.carousel_image_view);
            TextView textView = customView.findViewById(R.id.carousel_text_view);
            imageView.setImageResource(images.get(position));
            textView.setText("Title");
            return customView;
        }
    };

}

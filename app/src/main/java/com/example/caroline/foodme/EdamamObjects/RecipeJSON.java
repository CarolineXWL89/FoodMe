package com.example.caroline.foodme.EdamamObjects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.caroline.foodme.EdamamObjects.Ingredient;

/**
 * Created by princ on 03/05/2018.
 */
    public class RecipeJSON {
//
//        private String recipeName;
//        private String directions;
//        private String servings;
//        private String timeNeeded;
//        //private String ingredients; mRd
        private String ImageURL;
//        private String objectId;
//        private String ownerId;
        private String uri, label, source, url;
        private int yield;
        private float calories, totalWeight;
        private Ingredient[] ingredients;



        public RecipeJSON(){
        }

        public RecipeJSON(String imageURL,Ingredient[] ingredients) {

            this.ingredients = ingredients;
            ImageURL = imageURL;

        }


        public Ingredient[] getIngredients() {
            return ingredients;
        }

        public void setIngredients(Ingredient[] ingredients) {
            this.ingredients = ingredients;
        }

        public String getImageURL() {
            return ImageURL;
        }

        public void setImageURL(String url) {
            this.ImageURL = url;
        }

        protected RecipeJSON(Parcel in) {

            //ingredients = in.; TODO arraylist read impout
            ImageURL = in.readString();

        }
    }

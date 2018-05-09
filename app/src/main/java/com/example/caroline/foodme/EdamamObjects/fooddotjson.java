package com.example.caroline.foodme.EdamamObjects;

import com.example.caroline.foodme.EdamamObjects.EntitySearch;
import com.example.caroline.foodme.EdamamObjects.FoodEdamame;
import com.example.caroline.foodme.EdamamObjects.Ingredient;
import com.example.caroline.foodme.EdamamObjects.Measure;
import com.example.caroline.foodme.EdamamObjects.Parsed;

import java.util.ArrayList;

/**
 * Created by per6 on 4/27/18.
 */

public class fooddotjson {
    private int yield;
    private Ingredient ingredient = new Ingredient(0, "", "");
    private String[] uris = {"", ""};

    public fooddotjson(int yield){
        this.yield = yield;
    }

    public String[] findIngredient(EntitySearch entitySearch){
        //int random = (int) Math.random()* (entitySearches.size());
        //EntitySearch entitySearch = entitySearches.get(random);
        Parsed parsed = entitySearch.getParsed();
        FoodEdamame foodEdamame = parsed.getFood();
        String foodURI = foodEdamame.getUri();
        uris[0] = foodURI;

        Measure measure = parsed.getMeasure();
        String measureURI = measure.getUri();
        uris[1] = measureURI;

        return uris;
    }

    public void addIngredient(String[] twoURI){
        ingredient.setFoodURI(twoURI[0]);
        ingredient.setMeasureURI(twoURI[1]);
        ingredient.setQuantity(1);
    }
}

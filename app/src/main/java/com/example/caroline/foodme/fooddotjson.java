package com.example.caroline.foodme;

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

    public String[] findIngredient(ArrayList<EntitySearch> entitySearches){
        int random = (int) Math.random()* (entitySearches.size());
        EntitySearch entitySearch = entitySearches.get(random);
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

package com.example.caroline.foodme.EdamamObjects;

/**
 * Created by per6 on 4/27/18.
 */

public class fooddotjson {
    private int yield;
    private Ingredient ingredient = new Ingredient();
    private String[] uris = {"", ""};

    public fooddotjson(int yield){
        this.yield = yield;
    }

    public String[] findIngredient(EntitySearch entitySearch, int index){
        //int random = (int) Math.random()* (entitySearches.size());
        //EntitySearch entitySearch = entitySearches.get(random);
//        FoodEdamame foodEdamame = parsed.getFood(); changed 2018-05-11
        Hint hint = entitySearch.getHints().get(index);
        FoodEdamame foodEdamame = hint.getFood();
        String foodURI = foodEdamame.getUri();
        uris[0] = foodURI;

        int measurement = (int)(Math.random()*(hint.getMeasures().size()));
        Measure measure = hint.getMeasures().get(measurement); //changed 2018-05-11
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

package com.example.caroline.foodme.EdamamObjects;

import java.util.ArrayList;

/**
 * Created by princ on 09/04/2018.
 * Food Database API
 */

public class NutritionResponse {

    private String uri;
//    private int yield; changed on 2018-05-11
//    private int glycemicIndex;
//    private int totalWeight;
    private float calories;
    private ArrayList<NutritionInfo> totalNutrients = new ArrayList<>();
    private ArrayList<NutritionInfo> totalDaily = new ArrayList<>();
    private String[] dietLabels = {"", "", "", "", "", ""}; //TODO how to fix
    private String[] healthLabels = {"", "", "", "", "", "", "", "", ""};
//    private String[] dietLabels = {"balanced", "high-protein", "high-fiber", "low-fat", "low-carb", "low-sodium"};
//    private String[] healthLabels = {"vegan", "vegetarian", "dairy-free", "low-sugar", "low-fat-abs", "sugar-conscious", "fat-free", "gluten free", "wheat free"};

    //    private ParsedRequest ingredient;
    private SingleIngredientToParsed ingredient = new SingleIngredientToParsed();

    public NutritionResponse() {
        //required empty constructor thing
    }

    public String getUri() {
        return uri;
    }

    public float getCalories() {
        return calories;
    }

    public ArrayList<NutritionInfo> getTotalNutrients() {
        return totalNutrients;
    }

    public ArrayList<NutritionInfo> getTotalDaily() {
        return totalDaily;
    }

    public String[] getDietLabels() {
        return dietLabels;
    }

    public String[] getHealthLabels() {
        return healthLabels;
    }

    public SingleIngredientToParsed getIngredient() {
        return ingredient;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void setTotalNutrients(ArrayList<NutritionInfo> totalNutrients) {
        this.totalNutrients = totalNutrients;
    }

    public void setTotalDaily(ArrayList<NutritionInfo> totalDaily) {
        this.totalDaily = totalDaily;
    }

    public void setDietLabels(String[] dietLabels) {
        this.dietLabels = dietLabels;
    }

    public void setHealthLabels(String[] healthLabels) {
        this.healthLabels = healthLabels;
    }

    public void setIngredient(SingleIngredientToParsed ingredient) {
        this.ingredient = ingredient;
    }

    //    public int getYield() {
//        return yield;
//    }
//
//    public int getGlycemicIndex() {
//        return glycemicIndex;
//    }
//
//    public int getTotalWeight() {
//        return totalWeight;
//    }
//
//    public ParsedRequest getIngredient() {
//        return ingredient;
//    }
}

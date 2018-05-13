package com.example.caroline.foodme.EdamamObjects;

import java.util.ArrayList;

/**
 * Created by princ on 11/05/2018.
 * Recipe Search API
 */

public class RecipeActual {

    private String uri, recipeTitleLabel, imageURL, sourceSiteID, originalURL;
    private int yield;
    private float caloriesKCal;
    private float totalWeightGrams;
    private ArrayList<Ingredient> ingredients = new ArrayList<>(); //convert Ingredient[] --> AL
    private ArrayList<NutritionInfo> totalNutrients = new ArrayList<>();
    private ArrayList<NutritionInfo> totalDaily = new ArrayList<>();
    private ArrayList<String> dietLabels = new ArrayList<>();
    private ArrayList<String> healthLabels = new ArrayList<>();

    //TODO do we need parameters for this?
    public RecipeActual(/*String uri, String recipeTitleLabel, String imageURL, String sourceSiteID, String originalURL, int yield, float caloriesKCal, float totalWeightGrams, ArrayList<Ingredient> ingredients, ArrayList<NutritionInfo> totalNutrients, ArrayList<NutritionInfo> totalDaily, ArrayList<String> dietLabels, ArrayList<String> healthLabels*/) {
//        this.uri = uri;
//        this.recipeTitleLabel = recipeTitleLabel;
//        this.imageURL = imageURL;
//        this.sourceSiteID = sourceSiteID;
//        this.originalURL = originalURL;
//        this.yield = yield;
//        this.caloriesKCal = caloriesKCal;
//        this.totalWeightGrams = totalWeightGrams;
//        this.ingredients = ingredients;
//        this.totalNutrients = totalNutrients;
//        this.totalDaily = totalDaily;
//        this.dietLabels = dietLabels;
//        this.healthLabels = healthLabels;
    }

    public String getUri() {
        return uri;
    }

    public String getRecipeTitleLabel() {
        return recipeTitleLabel;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getSourceSiteID() {
        return sourceSiteID;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public int getYield() {
        return yield;
    }

    public float getCaloriesKCal() {
        return caloriesKCal;
    }

    public float getTotalWeightGrams() {
        return totalWeightGrams;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<NutritionInfo> getTotalNutrients() {
        return totalNutrients;
    }

    public ArrayList<NutritionInfo> getTotalDaily() {
        return totalDaily;
    }

    public ArrayList<String> getDietLabels() {
        return dietLabels;
    }

    public ArrayList<String> getHealthLabels() {
        return healthLabels;
    }
}

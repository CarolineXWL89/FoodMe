package com.example.caroline.foodme.EdamamObjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by princ on 11/05/2018.
 * Recipe Search API
 */

public class RecipeActual implements Serializable {

    private String uri, label, image, source, url, shareAs;
    private int yield;
    private float caloriesKCal;
    private float totalWeightGrams;
    private ArrayList<Ingredient> ingredients = new ArrayList<>(); //convert Ingredient[] --> AL
    private ArrayList<NutritionInfo> totalNutrients = new ArrayList<>();
    private ArrayList<NutritionInfo> totalDaily = new ArrayList<>();
    private ArrayList<String> dietLabels = new ArrayList<>();
    private ArrayList<String> healthLabels = new ArrayList<>();
    private ArrayList<String> cautions = new ArrayList<>(); //from Array

    private ArrayList<String> ingredientLines = new ArrayList<>();
//    private ArrayList<Ingredient> do we need this?

    //TODO do we need parameters for this?
    public RecipeActual(/*String uri, String label, String image, String source, String url, int yield, float caloriesKCal, float totalWeightGrams, ArrayList<Ingredient> ingredients, ArrayList<NutritionInfo> totalNutrients, ArrayList<NutritionInfo> totalDaily, ArrayList<String> dietLabels, ArrayList<String> healthLabels*/) {
//        this.uri = uri;
//        this.label = label;
//        this.image = image;
//        this.source = source;
//        this.url = url;
//        this.yield = yield;
//        this.caloriesKCal = caloriesKCal;
//        this.totalWeightGrams = totalWeightGrams;
//        this.ingredients = ingredients;
//        this.totalNutrients = totalNutrients;
//        this.totalDaily = totalDaily;
//        this.dietLabels = dietLabels;
//        this.healthLabels = healthLabels;
        super();
    }

    public String getUri() {
        return uri;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }

    public String getShareAs() {
        return shareAs;
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

    public ArrayList<String> getIngredientLines() {
        return ingredientLines;
    }


}

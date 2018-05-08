package com.example.caroline.foodme.EdamamObjects;

import java.util.ArrayList;

/**
 * Created by princ on 09/04/2018.
 */

public class NutritionResponse {

    private String uri;
    private float calories;
    private ArrayList<NutritionInfo> totalNutrients = new ArrayList<>();
    private ArrayList<NutritionInfo> totalDaily = new ArrayList<>();
    private String[] dietLabels = {"balanced", "high-protein", "high-fiber", "low-fat", "low-carb", "low-sodium"};
    private String[] healthLabels = {"vegan", "vegetarian", "dairy-free", "low-sugar", "low-fat-abs", "sugar-conscious", "fat-free", "gluten free", "wheat free"};

    public NutritionResponse(String uri, float calories, ArrayList totalNutrients, ArrayList totalDaily, String[] dietLabels, String[] healthLabels){
        this.uri = uri;
        this.calories = calories;
        this.totalNutrients = totalNutrients;
        this.totalDaily = totalDaily;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
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
}

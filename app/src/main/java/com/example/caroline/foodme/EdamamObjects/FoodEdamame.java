package com.example.caroline.foodme.EdamamObjects;

/**
 * Created by per6 on 4/17/18.
 * Food Database API
 * Recipe Search API
 */

public class FoodEdamame {
    private String uri = "";
    private  String label = "";

    //updated as of 2018-05-11
    private NutritionInfo nutrients = new NutritionInfo();
    private String source = "";

    public FoodEdamame(){
//        this.uri = uri;
//        this.label = label;
    }

    public String getUri() {
        return uri;
    }

    public String getLabel() {
        return label;
    }

    public NutritionInfo getNutrients() {
        return nutrients;
    }

    public String getSource() {
        return source;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setNutrients(NutritionInfo nutrients) {
        this.nutrients = nutrients;
    }

    public void setSource(String source) {
        this.source = source;
    }
}

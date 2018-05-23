package com.example.caroline.foodme.EdamamObjects;

/**
 * Created by princ on 07/05/2018.
 * Food Database API
 * Recipe Search API
 */

public class NutritionInfo {

    private String uri = "";
    private String label = "";
    private String unit = "";
    private float quantity = 0;

    public NutritionInfo(){
//        this.uri = uri;
//        this.label = label;
//        this.unit = unit;
//        this.quantity = quantity;
    }


    public String getUri() {
        return uri;
    }

    public String getLabel() {
        return label;
    }

    public String getUnit() {
        return unit;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}

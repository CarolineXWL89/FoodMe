package com.example.caroline.foodme.EdamamObjects;

/**
 * Created by princ on 07/05/2018.
 */

public class NutritionInfo {

    private String uri, label, unit;
    private float quantity;

    public NutritionInfo(String uri, String label, float quantity, String unit){
        this.uri = uri;
        this.label = label;
        this.unit = unit;
        this.quantity = quantity;
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
}

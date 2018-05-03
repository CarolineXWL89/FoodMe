package com.example.caroline.foodme;

/**
 * Created by per6 on 4/27/18.
 */

public class Ingredient {
    private int quantity;
    private String measureURI, foodURI;

    public Ingredient(int quantity, String measureURI, String foodURI){
        this.quantity = quantity;
        this.measureURI = measureURI;
        this.foodURI = foodURI;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMeasureURI(String measureURI) {
        this.measureURI = measureURI;
    }

    public void setFoodURI(String foodURI) {
        this.foodURI = foodURI;
    }
//tszgaergrweWBR53wv5bvqC WTe yhuey rsth
}

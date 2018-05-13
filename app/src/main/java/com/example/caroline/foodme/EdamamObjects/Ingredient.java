package com.example.caroline.foodme.EdamamObjects;

/**
 * Created by per6 on 4/27/18.
 * Food Database API
 * Recipe Search API
 */

public class Ingredient {
    private float quantity = 0;
    private String foodURI = "";

    //changed on 2018-05-11
    private Measure measure = new Measure();
    private float weight = 0;
    private FoodEdamame food = new FoodEdamame();
    private String measureURI = "";

    public Ingredient(){
//        this.quantity = quantity;
//        this.measureURI = measureURI;
//        this.foodURI = foodURI;
        //required empty constructor
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

package com.example.caroline.foodme;

/**
 * Created by princ on 23/04/2018.
 */

public class Hints {
    private FoodEdamame food = new FoodEdamame("", "");
    private Measure measure = new Measure("", "");
    public Hints(FoodEdamame food, Measure measure){
        this.food = food;
        this.measure = measure;
    }
}

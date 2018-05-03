package com.example.caroline.foodme;

import java.util.ArrayList;

/**
 * Created by princ on 23/04/2018.
 */

public class Hints {
    private FoodEdamame food = new FoodEdamame("", "");
    private ArrayList<Measure> measures = new ArrayList<>();
    public Hints(FoodEdamame food, ArrayList<Measure> measures){
        this.food = food;
        this.measures = measures;
    }
}

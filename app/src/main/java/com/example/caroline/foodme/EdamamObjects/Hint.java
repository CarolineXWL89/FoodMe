package com.example.caroline.foodme.EdamamObjects;

import java.util.ArrayList;

/**
 * Created by princ on 23/04/2018.
 * Food Database API
 */

public class Hint {
    private FoodEdamame food = new FoodEdamame();
    private ArrayList<Measure> measures = new ArrayList<>();

    public Hint(){
//        this.food = food;
//        this.measures = measures;
    }

    public FoodEdamame getFood() {
        return food;
    }

    public ArrayList<Measure> getMeasures() {
        return measures;
    }

    public void setFood(FoodEdamame food) {
        this.food = food;
    }

    public void setMeasures(ArrayList<Measure> measures) {
        this.measures = measures;
    }
}

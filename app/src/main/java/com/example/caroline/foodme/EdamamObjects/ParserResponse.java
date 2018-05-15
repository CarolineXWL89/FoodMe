package com.example.caroline.foodme.EdamamObjects;

/**
 * Created by princ on 12/05/2018.
 */

public class ParserResponse {

    private int quantity = 0;
    private String measure = "";
    private String food = "";
    private String foodId = "";
    private String foodURI = "";
    private int weight = 0;
    private int retainedWeight = 0;
    private String measuredURI = "";
    private String status = "";

    public ParserResponse() {
        //required empty constructor
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getFood() {
        return food;
    }
}

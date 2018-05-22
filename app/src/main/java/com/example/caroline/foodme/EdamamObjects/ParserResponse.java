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

    public String getFoodId() {
        return foodId;
    }

    public String getFoodURI() {
        return foodURI;
    }

    public int getWeight() {
        return weight;
    }

    public int getRetainedWeight() {
        return retainedWeight;
    }

    public String getMeasuredURI() {
        return measuredURI;
    }

    public String getStatus() {
        return status;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setFoodURI(String foodURI) {
        this.foodURI = foodURI;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setRetainedWeight(int retainedWeight) {
        this.retainedWeight = retainedWeight;
    }

    public void setMeasuredURI(String measuredURI) {
        this.measuredURI = measuredURI;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

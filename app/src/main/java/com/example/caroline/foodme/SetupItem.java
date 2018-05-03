package com.example.caroline.foodme;

/**
 * Created by michaelxiong on 3/13/18.
 * Placeholder class for objects from backendless
 * TODO update to reflect what we want to pull
 */

public class SetupItem {

    private String foodName, foodImage;

    public SetupItem(String foodName, String foodImage){
        this.foodName = foodName;
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }
}
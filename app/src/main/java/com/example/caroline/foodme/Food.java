package com.example.caroline.foodme;

/**
 * Created by michaelxiong on 3/13/18.
 */

public class Food {

    private String foodName, foodImage;

    public Food(String foodName, String foodImage){
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
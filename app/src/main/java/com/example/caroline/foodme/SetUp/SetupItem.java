package com.example.caroline.foodme.SetUp;

/**
 * Created by michaelxiong on 3/13/18.
 * Placeholder class for objects from backendless
 * TODO update to reflect what we want to pull
 */

public class SetupItem {

    private String foodName;
    private int foodImage;

    public SetupItem(String foodName, int foodImage){
        this.foodName = foodName;
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }

    @Override
    public String toString() {
        return "itemName: "+foodName+", itemImage: "+foodImage;
    }
}
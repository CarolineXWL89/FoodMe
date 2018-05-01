package com.example.caroline.foodme;

/**
 * Created by per6 on 4/17/18.
 */

public class Parsed {
        private FoodEdamame food = new FoodEdamame("", "");
        private int quantity;
        private Measure measure = new Measure("", "");

        public Parsed(FoodEdamame food, int quantity, Measure measure){
                this.food = food;
                this.quantity = quantity;
                this.measure = measure;
        }

        public FoodEdamame getFood() {
                return food;
        }

        public Measure getMeasure() {
                return measure;
        }

        public int getQuantity() {
                return quantity;
        }
}

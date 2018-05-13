package com.example.caroline.foodme.EdamamObjects;

/**
 * Created by per6 on 4/17/18.
 * Food Database API
 */

public class Parsed {
        private FoodEdamame food = new FoodEdamame();
        //updated as of 2018-05-11

//        private int quantity;
//        private Measure measure = new Measure("", "");

        public Parsed(){
            //required empty constructor
        }

        public FoodEdamame getFood() {
                return food;
        }

        /*public Measure getMeasure() {
                return measure;
        }

        public int getQuantity() {
                return quantity;
        }*/
}

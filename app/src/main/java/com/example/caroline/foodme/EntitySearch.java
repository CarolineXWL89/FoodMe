package com.example.caroline.foodme;

import java.util.ArrayList;

/**
 * Created by princ on 17/04/2018.
 * To get the information to plug into nutrition search
 */

public class EntitySearch{
    private String text;
    private Parsed parsed = new Parsed(new FoodEdamame("", ""), 0, new Measure("", ""));
    private Hints hints = new Hints(new FoodEdamame("", ""), new ArrayList<Measure>());
    private int page, numPages;

    private EntitySearch(String name, int page, int numPages ){
        text = name;
        this.page = page;
        this.numPages = numPages;

    }


    public Parsed getParsed() {
        return parsed;
    }
}

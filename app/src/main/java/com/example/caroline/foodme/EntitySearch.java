package com.example.caroline.foodme;

/**
 * Created by princ on 17/04/2018.
 * To get the information to plug into nutrition search
 */

public class EntitySearch{
    private String text;
    private Parsed parsed = new Parsed();
    private Hints hints = new Hints();
    private int page, numPages;

    private EntitySearch(String name, int page, int numPages ){
        text = name;
        this.page = page;
        this.numPages = numPages;

    }



}

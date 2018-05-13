package com.example.caroline.foodme.EdamamObjects;

import java.util.ArrayList;

/**
 * Created by princ on 17/04/2018.
 * To get the information to plug into nutrition search
 * Food Database API
 */

public class EntitySearch{
    private String text = "";
    private Parsed parsed = new Parsed();

    private ArrayList<Hint> hints = new ArrayList<>();
    private int page = 0;
    private int numPages = 0;

    public EntitySearch(){
        //required empty constructor
    }


    public Parsed getParsed() {
        return parsed;
    }

    public int getNumPages() {
        return numPages;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Hint> getHints() {
        return hints;
    }
}

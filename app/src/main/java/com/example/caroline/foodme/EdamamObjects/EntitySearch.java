package com.example.caroline.foodme.EdamamObjects;

import java.util.ArrayList;

/**
 * Created by princ on 17/04/2018.
 * To get the information to plug into nutrition search
 * Food Database API
 */

public class EntitySearch{
    private String text;
    private ParsedRequest parsed;
    private ArrayList<Hint> hints;
    private int page;
    private int numPages;

    public EntitySearch(){
        //required empty constructor
    }


    public ParsedRequest getParsed() {
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

    public int getPage() {
        return page;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setParsed(ParsedRequest parsed) {
        this.parsed = parsed;
    }

    public void setHints(ArrayList<Hint> hints) {
        this.hints = hints;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }
}

package com.example.caroline.foodme.EdamamObjects;

import com.example.caroline.foodme.EdamamObjects.Ingredient;

import java.util.ArrayList;

/**
 * Created by princ on 03/05/2018.
 * Recipe Search API (Hits)
 */
    public class RecipeJSON {

        private String query;
        private int from;
        private int to;
        //private String [][] params = new String[4][1]; //I THINK
        private int numFound;
        private boolean more;
        private ArrayList<Hit> hits = new ArrayList<>();

        public RecipeJSON(){
            //initialise
//            this.query = query;
//            this.from = from;
//            this.to = to;
//            this.numFound = numFound;
//            this.more = more;
//            this.hits = hits;
        }

    public String getQuery() {
        return query;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getNumFound() {
        return numFound;
    }

    public boolean isMore() {
        return more;
    }

    public ArrayList<Hit> getHits() {
        return hits;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public void setNumFound(int numFound) {
        this.numFound = numFound;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public void setHits(ArrayList<Hit> hits) {
        this.hits = hits;
    }
}

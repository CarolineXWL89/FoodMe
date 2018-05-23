package com.example.caroline.foodme.EdamamObjects;

/**
 * Created by princ on 23/04/2018.
 * Food Database API
 * Recipe Search API
 */

public class Measure {
    private String uri = "";
    private String label = "";

    public Measure(){
//        this.uri = uri;
//        this.label = label;
    }

    public String getUri() {
        return uri;
    }

    public String getLabel() {
        return label;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

package com.example.caroline.foodme;

/**
 * Created by princ on 23/04/2018.
 */

public class Measure {
    private String uri, label;

    public Measure(String label, String uri){
        this.uri = uri;
        this.label = label;
    }

    public String getUri() {
        return uri;
    }

    public String getLabel() {
        return label;
    }
}

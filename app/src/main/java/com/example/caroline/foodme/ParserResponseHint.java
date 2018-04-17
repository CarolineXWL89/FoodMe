package com.example.caroline.foodme;

import java.util.ArrayList;

/**
 * Created by michaelxiong on 4/13/18.
 */

public class ParserResponseHint {
    public ParserResponseItem food;
    public ArrayList<ParserResponseItem> measures;

    public ParserResponseHint() {
    }

    public ParserResponseItem getFood() {
        return food;
    }

    public void setFood(ParserResponseItem food) {
        this.food = food;
    }

    public ArrayList<ParserResponseItem> getMeasures() {
        return measures;
    }

    public void setMeasures(ArrayList<ParserResponseItem> measures) {
        this.measures = measures;
    }
}

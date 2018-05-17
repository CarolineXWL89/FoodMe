package com.example.caroline.foodme.SetUp;

/**
 * Created by michaelxiong on 5/14/18.
 */

public class SetUpChecklistItem {
    private String item;
    private boolean completed;

    public SetUpChecklistItem(String item, boolean completed){
        this.item = item;
        this.completed = completed;
    }

    public void setCompleted(){
        completed = true;
    }

    public String getItem() {
        return item;
    }

    public boolean isCompleted() {
        return completed;
    }
}

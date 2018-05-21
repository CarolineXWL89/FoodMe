package com.example.caroline.foodme.FavoritesFragment;

public class Favorites {
    private String backendlessID, edamamID;
    private Boolean Backendless;
    private int frequency;

    public Favorites(){

    }

    public Favorites(String backendlessID, String edamamID, Boolean backendless, int frequency) {
        this.backendlessID = backendlessID;
        this.edamamID = edamamID;
        Backendless = backendless;
        this.frequency = frequency;
    }

    public String getBackendlessID() {
        return backendlessID;
    }

    public void setBackendlessID(String backendlessID) {
        this.backendlessID = backendlessID;
    }

    public String getEdamamID() {
        return edamamID;
    }

    public void setEdamamID(String edamamID) {
        this.edamamID = edamamID;
    }

    public Boolean getBackendless() {
        return Backendless;
    }

    public void setBackendless(Boolean backendless) {
        Backendless = backendless;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}

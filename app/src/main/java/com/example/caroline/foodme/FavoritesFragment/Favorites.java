package com.example.caroline.foodme.FavoritesFragment;

public class Favorites {
    private String backendlessID, edamamID;
    private Boolean Backendless;

    public Favorites(){

    }

    public Favorites(String backendlessID, String edamamID, Boolean backendless) {
        this.backendlessID = backendlessID;
        this.edamamID = edamamID;
        Backendless = backendless;
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
}

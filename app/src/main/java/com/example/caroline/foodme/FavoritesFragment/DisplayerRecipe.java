package com.example.caroline.foodme.FavoritesFragment;

public class DisplayerRecipe {
    private String id, imageURL, name;
    private boolean backendless;

    public DisplayerRecipe(String id, String imageURL, boolean backendless, String name) {
        this.id = id;
        this.imageURL = imageURL;
        this.backendless = backendless;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isBackendless() {
        return backendless;
    }

    public void setBackendless(boolean backendless) {
        this.backendless = backendless;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

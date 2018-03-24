package com.example.caroline.foodme;

/**
 * Created by per6 on 3/23/18.
 */

public class Recipe {

    private String recipeName;
    private String recipe;
    private String objectId;
    private String ownerId;

    public Recipe(){

    }

    public Recipe(String recipeName, String recipe, String objectId, String ownerId) {
        this.recipeName = recipeName;
        this.recipe = recipe;
        this.objectId = objectId;
        this.ownerId = ownerId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}

package com.example.caroline.foodme.EdamamObjects;

/**
 * Created by princ on 11/05/2018.
 * Recipe Search API
 */

public class Hit {

    private RecipeActual recipe = new RecipeActual();
    private boolean bookmarked;
    private boolean bought;

    public Hit(RecipeActual recipe, boolean bookmarked, boolean bought){
        //initialise TODO Do we need this?
        this.recipe = recipe;
        this.bookmarked = bookmarked;
        this.bought = bought;
    }

    public RecipeActual getRecipe() {
        return recipe;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public boolean isBought() {
        return bought;
    }
}


package com.example.caroline.foodme.EdamamObjects.PojoObjectRecipe;

import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Hit {

    @SerializedName("bookmarked")
    private Boolean mBookmarked;
    @SerializedName("bought")
    private Boolean mBought;
    @SerializedName("recipe")
    private Recipe mRecipe;

    public Boolean getBookmarked() {
        return mBookmarked;
    }

    public void setBookmarked(Boolean bookmarked) {
        mBookmarked = bookmarked;
    }

    public Boolean getBought() {
        return mBought;
    }

    public void setBought(Boolean bought) {
        mBought = bought;
    }

    public Recipe getRecipe() {
        return mRecipe;
    }

    public void setRecipe(Recipe recipe) {
        mRecipe = recipe;
    }

}

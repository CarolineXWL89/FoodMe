
package com.example.caroline.foodme.EdamamObjects.PojoObjectRecipe;

import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Ingredient {

    @SerializedName("text")
    private String mText;
    @SerializedName("weight")
    private Double mWeight;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public Double getWeight() {
        return mWeight;
    }

    public void setWeight(Double weight) {
        mWeight = weight;
    }

}

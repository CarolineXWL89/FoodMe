package com.example.caroline.foodme;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by per6 on 3/23/18.
 */

public class RecipeJSON implements Parcelable {

    private String recipeName;
    private String directions;
    private String servings;
    private String timeNeeded;
    //private String ingredients; m
    private String ImageURL;
    private String objectId;
    private String ownerId;
    private String uri, label, source, url;
    private int yield;
    private float calories, totalWeight;
    private Ingredient[] ingredients;



    public RecipeJSON(){
    }

    public RecipeJSON(String recipeName, Ingredient[] ingredients, String directions, String servings, String timeNeeded, String imageURL, String ownerId) {
        this.recipeName = recipeName;
        this.directions = directions;
        this.servings = servings;
        this.timeNeeded = timeNeeded;
        this.ingredients = ingredients;
        ImageURL = imageURL;
        this.ownerId = ownerId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
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

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(String timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String url) {
        this.ImageURL = url;
    }

    protected RecipeJSON(Parcel in) {
        recipeName = in.readString();
        directions = in.readString();
        servings = in.readString();
        timeNeeded = in.readString();
        //ingredients = in.; TODO arraylist read impout
        ImageURL = in.readString();
        objectId = in.readString();
        ownerId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeName);
        dest.writeString(directions);
        dest.writeString(servings);
        dest.writeString(timeNeeded);
        //////dest.writeString(ingredients);
        dest.writeString(ImageURL);
        dest.writeString(objectId);
        dest.writeString(ownerId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RecipeJSON> CREATOR = new Parcelable.Creator<RecipeJSON>() {
        @Override
        public RecipeJSON createFromParcel(Parcel in) {
            return new RecipeJSON(in);
        }

        @Override
        public RecipeJSON[] newArray(int size) {
            return new RecipeJSON[size];
        }
    };
}

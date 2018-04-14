package com.example.caroline.foodme;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by per6 on 3/23/18.
 */

public class Recipe implements Parcelable {

    private String recipeName;
    private String directions;
    private String servings;
    private String timeNeeded;
    private String ImageURL;
    private String objectId;
    private String ownerId;

    public Recipe(){
    }

    public Recipe(String recipeName, String directions, String servings, String timeNeeded, String imageURL, String objectId, String ownerId) {
        this.recipeName = recipeName;
        this.directions = directions;
        this.servings = servings;
        this.timeNeeded = timeNeeded;
        ImageURL = imageURL;
        this.objectId = objectId;
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

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String url) {
        this.ImageURL = url;
    }

    protected Recipe(Parcel in) {
        recipeName = in.readString();
        directions = in.readString();
        servings = in.readString();
        timeNeeded = in.readString();
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
        dest.writeString(ImageURL);
        dest.writeString(objectId);
        dest.writeString(ownerId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
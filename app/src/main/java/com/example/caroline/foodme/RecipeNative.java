package com.example.caroline.foodme;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by per6 on 3/23/18.
 */

public class RecipeNative implements Parcelable {

    private String recipeName;
    private String directions;
    private String servings;
    private String timeNeeded;
    //private String ingredients; mRd
    private String ImageURL;
    private String objectId;
    private String ownerId;
    /*private String uri, label, source, url;
    private int yield;
    private float calories, totalWeight;*/
    private String ingredients;



    public RecipeNative(){
    }

    public RecipeNative(String recipeName, String ingredients, String directions, String servings, String timeNeeded, String imageURL, String ownerId) {
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        if(!(ingredients==null ||ingredients=="")) {
            String temp = ingredients;
            int indexComma = ingredients.indexOf(",");
            int length = ingredients.length();
            StringBuilder formatted = new StringBuilder();
            while (length > 0&&indexComma!=-1) {
                String ingr = temp.substring(0, indexComma);
                formatted.append(ingr + "\n");
                temp = temp.substring(indexComma + 2);
                indexComma = temp.indexOf(",");
                length = temp.length();
            }
            this.ingredients = formatted.toString();
        }
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String url) {
        this.ImageURL = url;
    }


    protected RecipeNative(Parcel in) {
        recipeName = in.readString();
        directions = in.readString();
        servings = in.readString();
        timeNeeded = in.readString();
        ImageURL = in.readString();
        objectId = in.readString();
        ownerId = in.readString();
        ingredients = in.readString();
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
        dest.writeString(ingredients);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RecipeNative> CREATOR = new Parcelable.Creator<RecipeNative>() {
        @Override
        public RecipeNative createFromParcel(Parcel in) {
            return new RecipeNative(in);
        }

        @Override
        public RecipeNative[] newArray(int size) {
            return new RecipeNative[size];
        }
    };
}
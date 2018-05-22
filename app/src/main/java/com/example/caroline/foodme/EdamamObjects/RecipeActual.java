package com.example.caroline.foodme.EdamamObjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by princ on 11/05/2018.
 * Recipe Search API
 */

public class RecipeActual implements Serializable {

    private String uri, label, image, source, url, shareAs;
    private int yield;
    private float caloriesKCal;
    private float totalWeightGrams;
    private ArrayList<Ingredient> ingredients = new ArrayList<>(); //convert Ingredient[] --> AL
    private ArrayList<NutritionInfo> totalNutrients = new ArrayList<>();
    private ArrayList<NutritionInfo> totalDaily = new ArrayList<>();
    private enum dietLabels{}; //TODO figure out what enums do
    private enum healthLabels{};
    //private ArrayList<String> cautions = new ArrayList<>(); //from Array

    private ArrayList<String> ingredientLines = new ArrayList<>();
//    private ArrayList<Ingredient> do we need this?

    public RecipeActual(/*String uri, String label, String image, String source, String url, int yield, float caloriesKCal, float totalWeightGrams, ArrayList<Ingredient> ingredients, ArrayList<NutritionInfo> totalNutrients, ArrayList<NutritionInfo> totalDaily, ArrayList<String> dietLabels, ArrayList<String> healthLabels*/) {
//        this.uri = uri;
//        this.label = label;
//        this.image = image;
//        this.source = source;
//        this.url = url;
//        this.yield = yield;
//        this.caloriesKCal = caloriesKCal;
//        this.totalWeightGrams = totalWeightGrams;
//        this.ingredients = ingredients;
//        this.totalNutrients = totalNutrients;
//        this.totalDaily = totalDaily;
//        this.dietLabels = dietLabels;
//        this.healthLabels = healthLabels;
        super();
    }

    public String getUri() {
        return uri;
    }

    public String getWorkingURI(String uri){
        //TODO change uri to this form --> -r=http%3A%2F%2Fwww.edamam.com%2Fontologies%2Fedamam.owl%23recipe_9b5945e03f05acbf9d69625138385408
        int l = uri.length();
        ArrayList<String> letterArray = new ArrayList<>();
        for(int i = 0; i < l; i++){
            String s = uri.substring(i, i + 1);
            switch (s){
                case ":":
                    letterArray.add("%3A");
                    break;
                case "/":
                    letterArray.add("%2F");
                    break;
                case "#":
                    letterArray.add("%23");
                    break;
                default:
                    letterArray.add(s);
                    break;

            }
        }
        String workingURI = letterArray.toString();
        return workingURI;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }

    public String getShareAs() {
        return shareAs;
    }

    public int getYield() {
        return yield;
    }

    public float getCaloriesKCal() {
        return caloriesKCal;
    }

    public float getTotalWeightGrams() {
        return totalWeightGrams;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<NutritionInfo> getTotalNutrients() {
        return totalNutrients;
    }

    public ArrayList<NutritionInfo> getTotalDaily() {
        return totalDaily;
    }

    public enum  getDietLabel{
        return dietLabels;
    }

    public ArrayList<String> getHealthLabels() {
        return healthLabels;
    }

    public ArrayList<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setShareAs(String shareAs) {
        this.shareAs = shareAs;
    }

    public void setYield(int yield) {
        this.yield = yield;
    }

    public void setCaloriesKCal(float caloriesKCal) {
        this.caloriesKCal = caloriesKCal;
    }

    public void setTotalWeightGrams(float totalWeightGrams) {
        this.totalWeightGrams = totalWeightGrams;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setTotalNutrients(ArrayList<NutritionInfo> totalNutrients) {
        this.totalNutrients = totalNutrients;
    }

    public void setTotalDaily(ArrayList<NutritionInfo> totalDaily) {
        this.totalDaily = totalDaily;
    }

    public void setDietLabels(ArrayList<String> dietLabels) {
        this.dietLabels = dietLabels;
    }

    public void setHealthLabels(ArrayList<String> healthLabels) {
        this.healthLabels = healthLabels;
    }

//    public void setCautions(ArrayList<String> cautions) {
//        this.cautions = cautions;
//    }

    public void setIngredientLines(ArrayList<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    @SuppressWarnings("never used")
    public String setFormattedIngrs(ArrayList<String> ingredientLines){
        StringBuilder formatted = new StringBuilder();
        for(String ingr : ingredientLines){
            formatted.append(ingr + "\n");
        }
        return formatted.toString();
    }


}

package com.example.caroline.foodme.GenerateFragment;

import android.support.v7.app.AppCompatActivity;

import com.example.caroline.foodme.EdamamObjects.RecipeJSON;
import com.example.caroline.foodme.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by michaelxiong on 4/10/18.
 */

public class RecipeGeneratorMethods extends AppCompatActivity {

    private ArrayList<String> ingredients = new ArrayList<>();
    private ArrayList<String> carbohydrates = new ArrayList<>();
    private ArrayList<String> proteins = new ArrayList<>();
    private ArrayList<String> vegetables = new ArrayList<>();
    private ArrayList<String> spices = new ArrayList<>();
    private ArrayList<String> oils = new ArrayList<>();
    private ArrayList<String> sauces = new ArrayList<>();
    private ArrayList<String> fruits = new ArrayList<>();
    private String stapleList;
    private String proteinList;
    private String vegetableList;
    private String spiceList;
    private String sauceList;
    private String oilList;
    private String fruitList;

    public RecipeGeneratorMethods(){
        //this.ingredients = ingredients;
        /*this.getLists();
        this.sortIngredients();*/
        //generateRecipe();
    }

    public void getLists(){
        InputStream stapleFileInputStream = getResources().openRawResource(R.raw.carbohydrates);
        stapleList = readTextFile(stapleFileInputStream);
        InputStream proteinFileInputStream = getResources().openRawResource(R.raw.proteins);
        proteinList = readTextFile(proteinFileInputStream);
        InputStream vegetableFileInputStream = getResources().openRawResource(R.raw.vegetables);
        vegetableList = readTextFile(vegetableFileInputStream);
        InputStream spiceFileInputStream = getResources().openRawResource(R.raw.spices);
        spiceList = readTextFile(spiceFileInputStream);
        InputStream sauceFileInputStream = getResources().openRawResource(R.raw.sauces);
        sauceList = readTextFile(sauceFileInputStream);
        InputStream oilFileInputStream = getResources().openRawResource(R.raw.oils);
        oilList = readTextFile(oilFileInputStream);
        InputStream fruitFileInputStream = getResources().openRawResource(R.raw.fruits);
        fruitList = readTextFile(fruitFileInputStream);
    }

    public void sortIngredients(){
        for(int i = 0; i < ingredients.size(); i++){
            String ingredient = ingredients.get(i);
            if(stapleList.indexOf(ingredient) != -1){
                carbohydrates.add(ingredient);
            }
            if(proteinList.indexOf(ingredient) != -1){
                proteins.add(ingredient);
            }
            if(vegetableList.indexOf(ingredient) != -1){
                vegetables.add(ingredient);
            }
            if(spiceList.indexOf(ingredient) != -1){
                spices.add(ingredient);
            }
            if(sauceList.indexOf(ingredient) != -1){
                sauces.add(ingredient);
            }
            if(oilList.indexOf(ingredient) != -1){
                oils.add(ingredient);
            }
            if(fruitList.indexOf(ingredient) != -1){
                fruits.add(ingredient);
            }
        }
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

    /*
    Goes through all raw text files and sorts them into an arrayList for each type --> then into an ArrayList of those ALs
    @return ArrayList<ArrayList<String>> of food ingredients
     */
    public ArrayList<ArrayList<String>> listAllIngredients(){
        //going through carbs
        ArrayList<ArrayList<String>> all = new ArrayList<>();
        ArrayList<String> carbs = new ArrayList<>();
        ArrayList<String> fruits = new ArrayList<>();
        ArrayList<String> oils = new ArrayList<>();
        ArrayList<String> proteins = new ArrayList<>();
        ArrayList<String> sauces = new ArrayList<>();
        ArrayList<String> spices = new ArrayList<>();
        ArrayList<String> veggies = new ArrayList<>();
        int lCarb = stapleList.length()
        int lFruit = fruitList.length();
        int lOil = oilList.length();
        int lProtein = proteinList.length();
        int lSauce = sauceList.length();
        int lSpice = spiceList.length();
        int lVeg = vegetableList.length();

        //going through carbohydrates file
        for(int i = 0; i < lCarb; i++){

            int startIndex = i;
            int endIndex = stapleList.indexOf(",");
        }

        return ;
    }

    public RecipeJSON generateRandomRecipe(){
        //TODO Decide on recipe templates


        RecipeJSON recipeJSONRand = new RecipeJSON();
        return recipeJSONRand;
    }

    public RecipeJSON userControlledGenerate(){
        //TODO Based on what user haves --> chooses some stuff

        RecipeJSON recipeJSONGen = new RecipeJSON();
        return recipeJSONGen;
    }

    public void userAddIngredients(){
        //how do we have them input?
    }

}

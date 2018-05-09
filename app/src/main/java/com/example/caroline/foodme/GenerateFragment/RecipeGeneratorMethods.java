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

    public final static int CARB_INDEX = 0;
    public final static int FRUIT_INDEX = 1;
    public final static int OIL_INDEX = 2;
    public final static int PROTEIN_INDEX = 3;
    public final static int SAUCE_INDEX = 4;
    public final static int SPICE_INDEX = 5;
    public final static int VEG_INDEX = 6;

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
        ArrayList<ArrayList<String>> all = new ArrayList<>(); //holds all ALs

        //list of all food items
        ArrayList<String> carbs = new ArrayList<>();
        ArrayList<String> fruits = new ArrayList<>();
        ArrayList<String> oils = new ArrayList<>();
        ArrayList<String> proteins = new ArrayList<>();
        ArrayList<String> sauces = new ArrayList<>();
        ArrayList<String> spices = new ArrayList<>();
        ArrayList<String> veggies = new ArrayList<>();

        //length of each raw food string
        int lCarb = stapleList.length();
        int lFruit = fruitList.length();
        int lOil = oilList.length();
        int lProtein = proteinList.length();
        int lSauce = sauceList.length();
        int lSpice = spiceList.length();
        int lVeg = vegetableList.length();


        //going through carbohydrates file
        String testCarb = stapleList;
        String testFruit = fruitList;
        String testOil = oilList;
        String testProtein = proteinList;
        String testSauce = sauceList;
        String testSpice = spiceList;
        String testVeg = vegetableList;

        //creating ALs of each food type then adding to AL of AL of Ss
        for(int i = 0; i < lCarb; i++){
            int startIndex = 0;
            int endIndex = testCarb.indexOf(",");
            if(i == endIndex){
                String test = testCarb.substring(startIndex, endIndex);
                carbs.add(test);
                testCarb = testCarb.substring(endIndex += 2);
            }
            all.add(carbs);
        }

        for(int i = 0; i < lFruit; i++){
            int startIndex = 0;
            int endIndex = testFruit.indexOf(",");
            if(i == endIndex){
                String test = testFruit.substring(startIndex, endIndex);
                fruits.add(test);
                testFruit = testFruit.substring(endIndex += 2);
            }
            all.add(fruits);
        }

        for(int i = 0; i < lOil; i++){
            int startIndex = 0;
            int endIndex = testOil.indexOf(",");
            if(i == endIndex){
                String test = testOil.substring(startIndex, endIndex);
                oils.add(test);
                testOil = testOil.substring(endIndex += 2);
            }
            all.add(oils);
        }

        for(int i = 0; i < lProtein; i++){
            int startIndex = 0;
            int endIndex = testProtein.indexOf(",");
            if(i == endIndex){
                String test = testProtein.substring(startIndex, endIndex);
                proteins.add(test);
                testProtein = testProtein.substring(endIndex += 2);
            }
            all.add(proteins);
        }

        for(int i = 0; i < lSauce; i++){
            int startIndex = 0;
            int endIndex = testSauce.indexOf(",");
            if(i == endIndex){
                String test = testSauce.substring(startIndex, endIndex);
                sauces.add(test);
                testSauce = testSauce.substring(endIndex += 2);
            }
            all.add(sauces);
        }

        for(int i = 0; i < lSpice; i++){
            int startIndex = 0;
            int endIndex = testSpice.indexOf(",");
            if(i == endIndex){
                String test = testSpice.substring(startIndex, endIndex);
                spices.add(test);
                testSpice = testSpice.substring(endIndex += 2);
            }
            all.add(spices);
        }

        for(int i = 0; i < lVeg; i++){
            int startIndex = 0;
            int endIndex = testVeg.indexOf(",");
            if(i == endIndex){
                String test = testVeg.substring(startIndex, endIndex);
                veggies.add(test);
                testVeg = testVeg.substring(endIndex += 2);
            }
            all.add(veggies);
        }

        return all;
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

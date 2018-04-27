package com.example.caroline.foodme;

import android.support.v7.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by michaelxiong on 4/10/18.
 */

public class RecipeGenerator extends AppCompatActivity {

    private ArrayList<String> ingredients;
    private ArrayList<String> staples = new ArrayList<String>();
    private ArrayList<String> proteins = new ArrayList<String>();
    private ArrayList<String> vegetables = new ArrayList<String>();
    private ArrayList<String> spices = new ArrayList<String>();
    private ArrayList<String> oils = new ArrayList<String>();
    private ArrayList<String> sauces = new ArrayList<String>();
    private ArrayList<String> fruits = new ArrayList<String>();
    private String stapleList;
    private String proteinList;
    private String vegetableList;
    private String spiceList;
    private String sauceList;
    private String oilList;
    private String fruitList;

    public RecipeGenerator( ArrayList<String> ingrdients){
        this.ingredients = ingrdients;

        getLists();
        sortIngredients();
        generateRecipe();
    }

    public void getLists(){
        InputStream stapleFileInputStream = getResources().openRawResource(R.raw.staples);
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
                staples.add(ingredient);
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

    public void generateRecipe(){

    }

}

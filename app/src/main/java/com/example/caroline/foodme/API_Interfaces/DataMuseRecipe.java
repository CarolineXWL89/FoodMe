package com.example.caroline.foodme.API_Interfaces;

import com.example.caroline.foodme.EdamamObjects.RecipeJSON;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by princ on 29/03/2018.
 */

public interface DataMuseRecipe {
    //TODO How many options do we want to have?
    String baseURL = "https://api.edamam.com/search/";

    @GET("recipes")
    Call<ArrayList<RecipeJSON>> getDatabaseRecipe(@Query("q") String keyWord, @Query("app_id") String appID, @Query("app_key") String appKey);


    @GET("recipes")
    Call<ArrayList<RecipeJSON>> getListedRecipes(@Query("q") String keyword, @Query("from") int start, @Query("to") int end, @Query("app_id") String appID, @Query("app_key") String appKey);

}

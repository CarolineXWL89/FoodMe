package com.example.caroline.foodme.API_Interfaces;

import com.example.caroline.foodme.EdamamObjects.RecipeJSON;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by princ on 29/03/2018.
 */

public interface DataMuseRecipe {

    String baseURL = "https://api.edamam.com/search";

    @GET("recipes")
    Call<RecipeJSON> getDatabaseRecipe(@Query("q") String keyWord, @Query("app_id") String appID, @Query("app_key") String appKey);


    //Caroline working on it right now
}

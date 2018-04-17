package com.example.caroline.foodme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by michaelxiong on 4/11/18.
 */

public interface DataMuseNutritionSearch {

    String baseURL = "https://api.edamam.com/api/food-database/";

    @GET("parser")
    Call<ParserResponse> getIngredientSearch(@Query("ingr") String ingredient, @Query("app_id") String appId, @Query("app_key") String appKey, @Query("page") String page);

}

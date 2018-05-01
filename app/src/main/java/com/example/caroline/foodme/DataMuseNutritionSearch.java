package com.example.caroline.foodme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by michaelxiong on 4/11/18.
 */

public interface DataMuseNutritionSearch {

    String baseURL = "https://api.edamam.com/api/food-database/";

    @POST("nutrients")
    Call<food.json> sendFood(@Query("app_id=") String apiID, @Query("app_key") String apiKey);
    //TODO Work on new Retrofit stuff
    //curl -d @food.json -H "Content-Type: application/json" "https://api.edamam.com/api/food-database/nutrients?app_id=${YOUR_APP_ID}&app_key=${YOUR_APP_KEY}"

    //Call<> //TODO figure out what cURL is
    /*@GET("parser")
    Call<ParserResponse> getIngredientSearch(@Query("ingr") String ingredient, @Query("app_id") String appId, @Query("app_key") String appKey, @Query("page") String page);*/

}
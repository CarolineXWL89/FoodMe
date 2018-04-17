package com.example.caroline.foodme;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by princ on 29/03/2018.
 */

public interface DataMuseNutritionIngr {

    String baseURL = "https://api.edamam.com/api/food-database/parser"; //nutrition
    String APP_ID = "06b894fb";
    String APP_KEY = "b6cdc3e30b89f8409d65e44eb788752f";

    @GET("ingrs")
    Call<ArrayList<NutritionRequest>> getIngrNutrient(@Query("ingr=") String foodIngr, @Query("app_id=") String appId, @Query("app_key") String appKey);



}

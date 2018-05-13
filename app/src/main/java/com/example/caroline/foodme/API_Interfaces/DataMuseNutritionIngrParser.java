package com.example.caroline.foodme.API_Interfaces;

import com.example.caroline.foodme.EdamamObjects.EntitySearch;
import com.example.caroline.foodme.EdamamObjects.Hint;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by princ on 29/03/2018.
 * Interface for the ParserResponse call
 * The Call gets an ArrayList of EntitySearches which is the JSON object of nutrient information
 * From Food Database API
 */

public interface DataMuseNutritionIngrParser {

    String baseURL = "https://api.edamam.com/api/food-database/parser"; //nutrition
    //String APP_ID_NUTRITION = "06b894fb";
    //String APP_KEY_NUTRITION = "b6cdc3e30b89f8409d65e44eb788752f";

    /**
     * Gets list of
     * @param foodIngr
     * @param appId
     * @param appKey
     * @return
     */
    @GET("ingrs")
    Call<ArrayList<EntitySearch>> getIngrNutrient(@Query("ingr=") String foodIngr, @Query("app_id=") String appId, @Query("app_key") String appKey);

    @GET("hints")
    Call<ArrayList<Hint>> getAllHints(@Query("ingr=") String foodIngr, @Query("page=") int pageNum, @Query("app_id=") String appId, @Query("app_key=") String appKey);


}

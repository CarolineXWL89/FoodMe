package com.example.caroline.foodme;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by princ on 17/04/2018.
 */

public class EntitySearchExtraction {

    private ArrayList<EntitySearch> entitySearches = new ArrayList<>();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(DataMuseNutritionIngr.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    DataMuseNutritionIngr api = retrofit.create(DataMuseNutritionIngr.class);

}

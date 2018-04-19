package com.example.caroline.foodme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EntitySearchExtraction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_search_extraction);

        final ArrayList<EntitySearch> entitySearches = new ArrayList<>();
        String foodSearched = "";
        String APP_ID = "06b894fb";
        String APP_KEY = "b6cdc3e30b89f8409d65e44eb788752f";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataMuseNutritionIngr.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DataMuseNutritionIngr api = retrofit.create(DataMuseNutritionIngr.class);


        Call<ArrayList<EntitySearch>> call = api.getIngrNutrient(foodSearched, APP_ID, APP_KEY);

        call.enqueue(new Callback<ArrayList<EntitySearch>>() {
            @Override
            public void onResponse(Call<ArrayList<EntitySearch>> call, Response<ArrayList<EntitySearch>> response) {
                entitySearches.clear();
                entitySearches.addAll(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<EntitySearch>> call, Throwable t) {
                //NOTHING YET
            }
        });

    }
}

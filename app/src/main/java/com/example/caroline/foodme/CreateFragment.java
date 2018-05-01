package com.example.caroline.foodme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateFragment extends Fragment {

    public static final String TAG = "fragments";
    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final ArrayList<EntitySearch> entitySearches = new ArrayList<>();
        String foodSearched = "";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataMuseNutritionIngr.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DataMuseNutritionIngr api = retrofit.create(DataMuseNutritionIngr.class);


        Call<ArrayList<EntitySearch>> call = api.getIngrNutrient(foodSearched, EdamamKeys.APP_ID, EdamamKeys.APP_KEY);

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

        final fooddotjson fooddotjson = new fooddotjson(); //TODO API stuff

        return inflater.inflate(R.layout.fragment_create, container, false);
    }

}

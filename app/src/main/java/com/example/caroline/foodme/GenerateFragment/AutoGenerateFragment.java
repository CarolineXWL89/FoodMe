package com.example.caroline.foodme.GenerateFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.caroline.foodme.API_Interfaces.DataMuseNutritionIngr;
import com.example.caroline.foodme.API_Interfaces.DataMuseNutritionSearch;
import com.example.caroline.foodme.EdamamNutritionKeys;
import com.example.caroline.foodme.EdamamObjects.EntitySearch;
import com.example.caroline.foodme.EdamamObjects.fooddotjson;
import com.example.caroline.foodme.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AutoGenerateFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_generate_fragment);

        final ArrayList<EntitySearch> entitySearches = new ArrayList<>();
        String foodSearched = "";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataMuseNutritionIngr.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DataMuseNutritionIngr api = retrofit.create(DataMuseNutritionIngr.class);


        Call<ArrayList<EntitySearch>> call = api.getIngrNutrient(foodSearched, EdamamNutritionKeys.APP_ID_NUTRITION, EdamamNutritionKeys.APP_KEY_NUTRITION);

        call.enqueue(new Callback<ArrayList<EntitySearch>>() {
            @Override
            public void onResponse(Call<ArrayList<EntitySearch>> call, Response<ArrayList<EntitySearch>> response) {
                entitySearches.clear();
                entitySearches.addAll(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<EntitySearch>> call, Throwable t) {
                Toast.makeText(AutoGenerateFragment.this, "Invalid food!!!", Toast.LENGTH_SHORT).show();
            }
        });

        final fooddotjson fooddotjson = new fooddotjson(1); //TODO decide how much they should have? Random?
        String[] uriTwo = fooddotjson.findIngredient(entitySearches);
        fooddotjson.addIngredient(uriTwo);

        DataMuseNutritionSearch apiFoodPackage = retrofit.create(DataMuseNutritionSearch.class);
        Call<fooddotjson> sendingCall = apiFoodPackage.sendFood(EdamamNutritionKeys.APP_ID_NUTRITION, EdamamNutritionKeys.APP_KEY_NUTRITION); //TODO Probably should include sending JSON at a point


        //return inflater.inflate(R.layout.fragment_create, container, false);
    }
}

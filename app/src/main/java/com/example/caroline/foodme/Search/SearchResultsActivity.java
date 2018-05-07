package com.example.caroline.foodme.Search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.caroline.foodme.API_Interfaces.DataMuseRecipe;
import com.example.caroline.foodme.EdamamObjects.EntitySearch;
import com.example.caroline.foodme.EdamamObjects.RecipeJSON;
import com.example.caroline.foodme.RecipeNative;
import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecyclerViewOnClick;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResultsActivity extends AppCompatActivity {

    private static final String TAG = "SearchResultsActivity";
    private List<RecipeNative> recipes;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SearchResultsAdapter searchResultsAdapter;
    private Context context;
    private RecyclerViewOnClick click;
    private Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        wireWidgets();
        //deals with search intent
        handleIntent(getIntent());








//
//        final ArrayList<EntitySearch> entitySearches = new ArrayList<>();
//        String foodSearched = "";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(DataMuseNutritionIngr.baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        DataMuseNutritionIngr api = retrofit.create(DataMuseNutritionIngr.class);
//
//
//        Call<ArrayList<EntitySearch>> call = api.getIngrNutrient(foodSearched, EdamamNutritionKeys.APP_ID_NUTRITION, EdamamNutritionKeys.APP_KEY_NUTRITION);
//
//        call.enqueue(new Callback<ArrayList<EntitySearch>>() {
//            @Override
//            public void onResponse(Call<ArrayList<EntitySearch>> call, Response<ArrayList<EntitySearch>> response) {
//                entitySearches.clear();
//                entitySearches.addAll(response.body());
//            }
//            @Override
//            public void onFailure(Call<ArrayList<EntitySearch>> call, Throwable t) {
//                //NOTHING YET
//            }
//        });
//
//        final fooddotjson fooddotjson = new fooddotjson(1); //TODO decide how much they should have? Random?
//        String[] uriTwo = fooddotjson.findIngredient(entitySearches);
//        fooddotjson.addIngredient(uriTwo);
//
//        DataMuseNutritionSearch apiFoodPackage = retrofit.create(DataMuseNutritionSearch.class);
//        Call<fooddotjson> sendingCall = apiFoodPackage.sendFood(EdamamNutritionKeys.APP_ID_NUTRITION, EdamamNutritionKeys.APP_KEY_NUTRITION); //TODO Probably should include sending JSON at a point
//
//
//        return inflater.inflate(R.layout.fragment_create, container, false);
//
//


        final ArrayList<RecipeJSON> recipeJSON = new ArrayList<>();
        String keyword = "";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataMuseRecipe.baseURL}
               // .addConverterFactory(GsonConverterFactory.create.()).build();

















    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            //gets query and saves search to recent searches
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    MyRecentSuggestionProvider.AUTHORITY, MyRecentSuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            showResults(query);
        }
    }

    private void showResults(String query) {
        //displays the results
        StringBuilder whereClause = new StringBuilder();
        whereClause.append("recipeName like '%" + query + "%'");
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause.toString());
        //does the search on backendless
        //todo implement api search too
        Backendless.Data.of(RecipeNative.class).find(queryBuilder, new AsyncCallback<List<RecipeNative>>() {
            @Override
            public void handleResponse(List<RecipeNative> response) {
                recipes.addAll(response);
                searchResultsAdapter.notifyDataSetChanged();
            }
            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, "handleFault: " + fault.getMessage());
                Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void wireWidgets() {
        recipes = new ArrayList<>();
        //wires toolbar
        toolbar = findViewById(R.id.toolbar_search_results_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //todo add sorting method for search results
        // mine vesurs nirmal
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //todo on back pressed take back
            }
        });
        //wires recycler view etc..
        recyclerView = findViewById(R.id.search_results_recycle);
        click = new RecyclerViewOnClick() {
            @Override
            public void onClick(View v, int pos) {
                //todo load recipe
                Toast.makeText(SearchResultsActivity.this, "We are making "+ recipes.get(pos).getRecipeName(), Toast.LENGTH_LONG).show();
            }
        };
        searchResultsAdapter = new SearchResultsAdapter(recipes,this,click);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchResultsAdapter);
        registerForContextMenu(recyclerView);

    }
}
//
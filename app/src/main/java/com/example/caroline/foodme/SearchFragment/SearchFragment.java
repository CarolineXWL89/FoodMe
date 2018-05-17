package com.example.caroline.foodme.SearchFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.caroline.foodme.API_Interfaces.DataMuseNutritionIngrParser;
import com.example.caroline.foodme.API_Interfaces.DataMuseRecipe;
import com.example.caroline.foodme.EdamamObjects.Hit;
import com.example.caroline.foodme.EdamamObjects.RecipeActual;
import com.example.caroline.foodme.EdamamObjects.RecipeJSON;
import com.example.caroline.foodme.EdamamRecipeKeys;
import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecipeNative;
import com.example.caroline.foodme.RecyclerViewOnClick;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maylisw on 3/21/18.
 */


public class SearchFragment extends Fragment {

    public static final String TAG = "fragments";
    private ArrayList<RecipeNative> recipes;

    private ArrayList<RecipeActual> recipes2;

    private FloatingActionButton clearButton, submit;
    private ArrayList<String> ingredients;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    //private SearchResultsAdapter searchResultsAdapter;
    private Context context;
    private View rootView;
    private EditText newRecipeEditText;
    private IngredientSearchAdapter ingredientSearchAdapter;
    private ImageButton plusButton;

    //todo make edit text with UI of text view
    //todo search with api
    //todo have plusButton buttn
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        context = rootView.getContext();
        wireDaStuff();
        return rootView;
    }
  
    private void wireDaStuff() {
        recipes = new ArrayList<>();
        ingredients = new ArrayList<>();
        ingredients.add(" ");
        newRecipeEditText = rootView.findViewById(R.id.newIngredientSearchEdit);

        //recycler view wired
        recyclerView = rootView.findViewById(R.id.search_recipe_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerViewOnClick listener = new RecyclerViewOnClick() {
            @Override
            public void onClick(View v, int pos) {
                //todo load recipe
                Toast.makeText(context, "We are making "+ recipes.get(pos).getRecipeName(), Toast.LENGTH_LONG).show();
            }
        };
        ingredientSearchAdapter = new IngredientSearchAdapter(ingredients, listener,context);
        ingredients.remove(0);
        recyclerView.setAdapter(ingredientSearchAdapter);
        registerForContextMenu(recyclerView);

        //wires buttons
        submit = rootView.findViewById(R.id.button_submit_search);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ingredients.size()>0){
                    ArrayList<String> theStuff = ingredients;
                    Log.d(TAG, "onClick: " + theStuff.get(0));
                    Log.d(TAG, "onClick: clicked");
                    backendlessSearchByIngredient(theStuff);
                }else{
                    Toast.makeText(context, "Please Enter Ingredients", Toast.LENGTH_SHORT).show();
                }

            }
        });
        clearButton = rootView.findViewById(R.id.button_clear_ingredient);
        plusButton =rootView.findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredients.add( newRecipeEditText.getText().toString());
                Log.d(TAG, "onClick: ingredients"+ingredients.toString());
                recyclerView.smoothScrollToPosition(ingredients.size()-1);
                ingredientSearchAdapter.notifyDataSetChanged();
                newRecipeEditText.setText("");

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ingredients.clear();
                ingredientSearchAdapter.notifyDataSetChanged();
            }

        });
    }

    private void backendlessSearchByIngredient(final ArrayList<String> theStuff) {
        //creates backendless where clause
        StringBuilder whereClause = new StringBuilder();
        whereClause.append("ingredients like '%"+theStuff.get(0)+"%'");
        for(String ingredient:theStuff) {
            whereClause.append("and ingredients like '%" + ingredient + "%'");
        }
        //seraches backendless by ingredients

        //todo implement api
        final ArrayList<RecipeJSON> listOfSearches = new ArrayList<>();
        if(listOfSearches.equals(null)){
            Log.d("Instance ", "null");
        }
        else{
            Log.d("Instance ", "not null");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataMuseRecipe.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        int ingrLength = 0;
        StringBuilder allIngrSearch = new StringBuilder();
        for(String ingredient : theStuff){
            allIngrSearch.append(ingredient + "%20");
            ingrLength += ingredient.length();
        }
//        String search = allIngrSearch.toString().substring(0, allIngrSearch.length() - 2);
        int numberIngrs = theStuff.size();
        Log.d("Ingr number ", "" + numberIngrs);
        int indexEnd = ingrLength + 3 * (numberIngrs - 1);
        Log.d("End index ", "" + indexEnd);
        String currentSearch = allIngrSearch.toString().substring(0, allIngrSearch.length() - 3);
        Log.d("Current search ", currentSearch);
        final DataMuseRecipe recipeAPI = retrofit.create(DataMuseRecipe.class);

        if(!(listOfSearches.equals(null))){
            while(numberIngrs > 0){
                currentSearch = currentSearch.substring(0, indexEnd); //TODO out of bounds exception
                Log.d("New Currents", "" + numberIngrs + ": " + currentSearch);
                indexEnd = currentSearch.lastIndexOf("%20");
                Log.d("Last Index %20", ""+ indexEnd);
                Call<ArrayList<RecipeJSON>> originalCall = recipeAPI.getListedRecipes(currentSearch, 0, 40, EdamamRecipeKeys.APP_ID_RECIPE, EdamamRecipeKeys.APP_KEY_RECIPE);
                originalCall.enqueue(new Callback<ArrayList<RecipeJSON>>() {
                    @Override
                    public void onResponse(Call<ArrayList<RecipeJSON>> call, Response<ArrayList<RecipeJSON>> response) {
                        if(response.code() == 400 || response.equals(null)){
                            //testing for bad request (HTTP 400)
                            Log.d("Response error status ", "" + response.code());
                            Gson gson = new Gson();
                            TypeAdapter<RecipeJSON> adapter = gson.getAdapter(RecipeJSON.class);
                            if(response.errorBody() != null){
                                try {
                                    RecipeJSON recipeJSON = adapter.fromJson(response.errorBody().string());
                                    listOfSearches.add(recipeJSON);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        else{
                            listOfSearches.addAll((Collection<? extends RecipeJSON>) response.body().get(0));
                            Log.d("Status search ", "Successfully searched " + listOfSearches.get(0).getQuery());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<RecipeJSON>> call, Throwable t){
                        Log.d("Status search ", "failed search");
                    }
                });
                numberIngrs--;
            }
        }
        else{
            Log.d("ListSearches Status ", "Why am I null");
        }


        for(RecipeJSON recipeJSON : listOfSearches){
            ArrayList<Hit> hits = recipeJSON.getHits();
            for(Hit hit : hits){
                RecipeActual recipeActual = hit.getRecipe();
                recipes2.add(recipeActual);
            }
        }

        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause.toString());
        //getting results from both recipe lists into 1 intent
        Backendless.Data.of(RecipeNative.class).find(queryBuilder, new AsyncCallback<List<RecipeNative>>() {
            @Override
            public void handleResponse(List<RecipeNative> response) {
                recipes.clear();
                recipes.addAll(response);
                if(recipes.size()!=0 || recipes2.size()!=0) {
                    //starts results activity
                    Intent i = new Intent(getActivity(), SearchResultsDisplayer.class);
                    i.putExtra("the_stuff", recipes);
                    i.putExtra("other_stuff", recipes2);
                    startActivity(i);
                }
                else {
                    Toast.makeText(context, "No Results Found", Toast.LENGTH_SHORT).show();
                    //todo display empty result page
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, "handleFault: "+fault.getMessage());
                Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}

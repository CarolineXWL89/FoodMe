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
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.caroline.foodme.RecipeNative;
import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecyclerViewOnClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maylisw on 3/21/18.
 */


public class SearchFragment extends Fragment {

    public static final String TAG = "fragments";
    private ArrayList<RecipeNative> recipes;
    private FloatingActionButton addRecipe, submit;
    private ArrayList<String> ingredients;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    //private SearchResultsAdapter searchResultsAdapter;
    private Context context;
    private View rootView;
    private EditText newRecipeEditText;
    private IngredientSearchAdapter ingredientSearchAdapter;

    //todo make edit text with UI of text view
    //todo search with api
    //todo have clear buttn
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
        addRecipe = rootView.findViewById(R.id.button_new_ingredient);
        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredients.add( newRecipeEditText.getText().toString());
                Log.d(TAG, "onClick: ingredients"+ingredients.toString());
                recyclerView.smoothScrollToPosition(ingredients.size()-1);
                ingredientSearchAdapter.notifyDataSetChanged();
                newRecipeEditText.setText("");

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
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause.toString());
        Backendless.Data.of(RecipeNative.class).find(queryBuilder, new AsyncCallback<List<RecipeNative>>() {
            @Override
            public void handleResponse(List<RecipeNative> response) {
                recipes.clear();
                recipes.addAll(response);
                if(recipes.size()!=0) {
                    //starts results activity
                    Intent i = new Intent(getActivity(), SearchResultsDisplayer.class);
                    i.putExtra("the_stuff", recipes);
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
/*
        //todo who made this? fix
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
                //NOTHING YET
            }
        });

        final fooddotjson fooddotjson = new fooddotjson(1); //TODO decide how much they should have? Random?
        String[] uriTwo = fooddotjson.findIngredient(entitySearches);
        fooddotjson.addIngredient(uriTwo);

        DataMuseNutritionSearch apiFoodPackage = retrofit.create(DataMuseNutritionSearch.class);
        Call<fooddotjson> sendingCall = apiFoodPackage.sendFood(EdamamNutritionKeys.APP_ID_NUTRITION, EdamamNutritionKeys.APP_KEY_NUTRITION); //TODO Probably should include sending JSON at a point


        return inflater.inflate(R.layout.fragment_create, container, false);*/
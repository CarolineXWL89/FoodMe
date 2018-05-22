package com.example.caroline.foodme.SearchFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.caroline.foodme.EdamamObjects.RecipeActual;
import com.example.caroline.foodme.RecipeDisplay.RecipeDisplayTemp;
import com.example.caroline.foodme.RecipeNative;
import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecyclerViewOnClick;
import com.example.caroline.foodme.Search.SearchResultsAdapter;

import java.util.ArrayList;

/**
 * Created by nicPorcu on 4/13/18.
 */

public class SearchResultsDisplayer extends AppCompatActivity {


    private static final String TAG ="SearchResultsDisplayer";
    private ArrayList recipes;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SearchResultsAdapter searchResultsAdapter;
    private Context context;
    private RecyclerViewOnClick click;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_displayer);

        //gets arraylist of search results from intent
        recipes = new ArrayList();
        Intent i = getIntent();
        ArrayList<RecipeNative> r = i.getParcelableArrayListExtra("the_stuff");
        ArrayList<RecipeActual> r2 = (ArrayList<RecipeActual>) i.getSerializableExtra("other_stuff");

        recipes.addAll(r);
        recipes.addAll(r2);
        for (int j = 0; j<recipes.size(); j++) {
            if (recipes.get(j) != null){
                if(recipes.get(j).getClass().getName().equals("RecipeNative")){
                    Log.d(TAG, "onCreate: " + ((RecipeNative) recipes.get(j)).getRecipeName());
                }
                else{
                    Log.d(TAG, "onCreate: " + ((RecipeActual) recipes.get(j)).getLabel());
                }

            }else{
                Log.d(TAG, "onCreate: null");
                recipes.remove(j);
            }
        }

        wireWidgets();


        //searchResultsAdapter.notifyDataSetChanged();

    }

    private void wireWidgets() {
        //wires toolbar
        toolbar = findViewById(R.id.toolbar_search_results_displayer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //todo add sorting method for search results
        // mine vesurs nirmal
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //todo go back on back clicked
            }
        });

        //wires recyclerview
        recyclerView = findViewById(R.id.search_results_recycler_view);
        final Intent i = new Intent(getBaseContext(), RecipeDisplayTemp.class);
        click = new RecyclerViewOnClick() {
            @Override
            public void onClick(View v, int pos) {
                if(recipes.get(pos).getClass().getName().equals("RecipeNative")){
                    RecipeNative recipeNative = (RecipeNative) recipes.get(pos);
                    i.putExtra("recipe_native_show", recipeNative);
                    startActivity(i);
                    Toast.makeText(SearchResultsDisplayer.this, "We are making "+ ((RecipeNative) recipes.get(pos)).getRecipeName(), Toast.LENGTH_LONG).show();
                }
                else{
                    RecipeActual recipeActual = (RecipeActual) recipes.get(pos);
                    i.putExtra("recipe_actual_show", recipeActual);
                    startActivity(i);
                    Toast.makeText(SearchResultsDisplayer.this, "We are making " + ((RecipeActual) recipes.get(pos)).getLabel(), Toast.LENGTH_SHORT).show();
                }

                //todo load recipe
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

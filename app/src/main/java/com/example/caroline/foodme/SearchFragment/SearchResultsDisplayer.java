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

import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecipeJSON;
import com.example.caroline.foodme.RecyclerViewOnClick;
import com.example.caroline.foodme.Search.SearchResultsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicPorcu on 4/13/18.
 */

public class SearchResultsDisplayer extends AppCompatActivity {


    private static final String TAG ="SearchResultsDisplayer";
    private List<RecipeJSON> recipes;
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
        recipes = new ArrayList<>();
        Intent i = getIntent();
        ArrayList<RecipeJSON> r = i.getParcelableArrayListExtra("the_stuff");
        recipes.addAll(r);
        for (int j = 0; j<recipes.size(); j++) {
            if (recipes.get(j) != null){
                Log.d(TAG, "onCreate: " + recipes.get(0).getRecipeName());
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
        click = new RecyclerViewOnClick() {
            @Override
            public void onClick(View v, int pos) {
                Toast.makeText(SearchResultsDisplayer.this, "We are making "+ recipes.get(pos).getRecipeName(), Toast.LENGTH_LONG).show();
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

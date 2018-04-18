package com.example.caroline.foodme;

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

import java.util.ArrayList;
import java.util.List;

public class SearchResultsDisplayer extends AppCompatActivity {


    private static final String TAG ="SearchResultsDisplayer";
    private List<Recipe> recipes;
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
        Log.d(TAG, "onCreate: hi");
        recipes = new ArrayList<>();
        Intent i = getIntent();
        ArrayList<Recipe> r = i.getParcelableArrayListExtra("the_stuff");

        recipes.addAll(r);
        for (int j=0; j<recipes.size(); j++) {
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

        recyclerView=findViewById(R.id.search_results_recycler_view);
        toolbar=findViewById(R.id.toolbar_search_results_displayer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //What to do on back clicked
            }
        });
        click=new RecyclerViewOnClick() {
            @Override
            public void onClick(View v, int pos) {
                Toast.makeText(SearchResultsDisplayer.this, "We are making "+ recipes.get(pos).getRecipeName(), Toast.LENGTH_LONG).show();
            }
        };
        searchResultsAdapter= new SearchResultsAdapter(recipes,this,click);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerViewOnClick listener = new RecyclerViewOnClick() {
            @Override
            public void onClick(View v, int pos) {
                //todo make onclick
                //todo load recipe
                Toast.makeText(context, "We are making "+ recipes.get(pos).getRecipeName(), Toast.LENGTH_LONG).show();
            }
        };
        recyclerView.setAdapter(searchResultsAdapter);
        registerForContextMenu(recyclerView);

    }
}

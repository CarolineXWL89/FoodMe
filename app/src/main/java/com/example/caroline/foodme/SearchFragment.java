package com.example.caroline.foodme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    public static final String TAG = "fragments";
    private ArrayList<Recipe> recipies;
    private FloatingActionButton addRecipe, submit;
    private ArrayList<String> ingredients;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    //private SearchResultsAdapter searchResultsAdapter;
    private Context context;
    private View rootView;
    private SearchView simpleSearchView;
    private IngredientSearchAdapter ingredientSearchAdapter;
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
        // Inflate the layout for this fragment
        wireDaStuff();
        return rootView;
    }
//
    private void doMySearch(String query) {
        //todo make call to backendless and display as recycler view
        //todo get user id
        StringBuilder whereClause = new StringBuilder();
        //whereClause.append( "recipeName like '%Bread%'" );
        whereClause.append( "recipeName like '%"+query+"%'" );
       // String whereClause="recipeName = '"+query+"'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause.toString());
        Backendless.Data.of(Recipe.class).find(queryBuilder, new AsyncCallback<List<Recipe>>() {
            @Override
            public void handleResponse(List<Recipe> response) {
                Log.d(TAG, "handleResponse: "+response.size());
                recipies.clear();
                recipies.addAll(response);
                //searchResultsAdapter.notifyDataSetChanged();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, "handleFault: "+fault.getMessage());
            }
        });


    }

    private void wireDaStuff() {
        recipies = new ArrayList<>();
        ingredients=new ArrayList<>();
        ingredients.add(" ");
        recyclerView = rootView.findViewById(R.id.search_recipe_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerViewOnClick listener = new RecyclerViewOnClick() {
            @Override
            public void onClick(View v, int pos) {
                //todo make onclick
                //todo load recipe
                Toast.makeText(context, "We are making "+ recipies.get(pos).getRecipeName(), Toast.LENGTH_LONG).show();
            }
        };
        ingredientSearchAdapter= new IngredientSearchAdapter(ingredients, listener,context);
        //searchResultsAdapter = new SearchResultsAdapter(recipies, context, listener);

        recyclerView.setAdapter(ingredientSearchAdapter);
        registerForContextMenu(recyclerView);
        submit=rootView.findViewById(R.id.button_submit_search);
        addRecipe=rootView.findViewById(R.id.button_new_ingredient);
        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredients.add("");
                ingredientSearchAdapter.notifyDataSetChanged();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String > theStuff=ingredientSearchAdapter.getIngredients();
                Log.d(TAG, "onClick: clicked");
                backendlessSearchByIngredient(theStuff);
//                Intent i=new Intent(getActivity(),SearchResultsDisplayer.class);
//                i.putExtra("the_stuff",theStuff);
//                startActivity(i);
                //todo create a display activity
            }
        });
        simpleSearchView = (SearchView) rootView.findViewById(R.id.simpleSearchView); // inititate a search view
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doMySearch(query);
                return false;
            }

            @Override//
            public boolean onQueryTextChange(String newText) {
                doMySearch(newText);
                return false;
            }
        });
    }

    private void backendlessSearchByIngredient(final ArrayList<String> theStuff) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append("ingredients like '%"+theStuff.get(0)+"%'");
        for(String ingredient:theStuff) {
            whereClause.append("and ingredients like '%" + ingredient + "%'");
        }
        Log.d(TAG, "backendlessSearchByIngredient: "+whereClause.toString());
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause.toString());
        Backendless.Data.of(Recipe.class).find(queryBuilder, new AsyncCallback<List<Recipe>>() {
            @Override
            public void handleResponse(List<Recipe> response) {
                Log.d(TAG, "handleResponse: "+response.size());
                Log.d(TAG, "handleResponse: "+response.get(0).getRecipeName());
                recipies.clear();
                recipies.addAll(response);
                ArrayList<Recipe> r=recipies;
                //searchResultsAdapter.notifyDataSetChanged();
                Intent i=new Intent(getActivity(),SearchResultsDisplayer.class);
                i.putExtra("the_stuff", r);
                //startActivity(i);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, "handleFault: "+fault.getMessage());
            }
        });



    }
}

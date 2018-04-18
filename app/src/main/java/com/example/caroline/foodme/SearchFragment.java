package com.example.caroline.foodme;

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
    private EditText newRecipeEditText;

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

    private void wireDaStuff() {
        recipies = new ArrayList<>();
        ingredients=new ArrayList<>();
        ingredients.add(" ");
        recyclerView = rootView.findViewById(R.id.search_recipe_recycler_view);
        newRecipeEditText=rootView.findViewById(R.id.newIngredientSearchEdit);
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
        ingredients.remove(0);
        recyclerView.setAdapter(ingredientSearchAdapter);
        registerForContextMenu(recyclerView);
        submit = rootView.findViewById(R.id.button_submit_search);
        addRecipe=rootView.findViewById(R.id.button_new_ingredient);
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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String > theStuff = ingredientSearchAdapter.getIngredients();
                Log.d(TAG, "onClick: clicked");
                backendlessSearchByIngredient(theStuff);
//                Intent i=new Intent(getActivity(),SearchResultsDisplayer.class);
//                i.putExtra("the_stuff",theStuff);
//                startActivity(i);
                //todo create a display activity
            }
        });



    }

    private void backendlessSearchByIngredient(final ArrayList<String> theStuff) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append("ingredients like '%"+theStuff.get(0)+"%'");
        for(String ingredient:theStuff) {
            whereClause.append("and ingredients like '%" + ingredient + "%'");
        }
        Log.d(TAG, "backendlessSearchByIngredient: " +whereClause.toString());
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause.toString());
        Backendless.Data.of(Recipe.class).find(queryBuilder, new AsyncCallback<List<Recipe>>() {
            @Override
            public void handleResponse(List<Recipe> response) {
                Log.d(TAG, "handleResponse: "+response.size());
//                Log.d(TAG, "handleResponse: "+response.get(0).getRecipeName());
                recipies.clear();
                recipies.addAll(response);
                ArrayList<Recipe> r=recipies;
                //searchResultsAdapter.notifyDataSetChanged();
                if(r.size()!=0) {
                    Intent i = new Intent(getActivity(), SearchResultsDisplayer.class);
                    i.putExtra("the_stuff", r);
                    startActivity(i);
                }
                else {
                    Toast.makeText(context, "No Results Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, "handleFault: "+fault.getMessage());
            }
        });



    }
}

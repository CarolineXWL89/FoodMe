package com.example.caroline.foodme;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class SearchFragment extends Fragment {

    public static final String TAG = "fragments";
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doMySearch("Bread");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent intent = getActivity().getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    private void doMySearch(String query) {
        //todo make call to backendless and display as recycler view
//        //todo get user id
        String whereClause="recipeName = '"+query+"'";
        DataQueryBuilder queryBuilder=DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        Backendless.Data.of(Recipe.class).find(queryBuilder, new AsyncCallback<List<Recipe>>() {
            @Override
            public void handleResponse(List<Recipe> response) {
                Log.d(TAG, "handleResponse: "+response.size());


            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d(TAG, "handleFault: "+fault.getMessage());

            }
        });





    }

}

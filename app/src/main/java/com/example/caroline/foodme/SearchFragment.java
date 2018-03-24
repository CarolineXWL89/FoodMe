package com.example.caroline.foodme;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    public static final String TAG = "fragments";
    private ArrayList<Result> results;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SearchResultsAdapter searchResultsAdapter;
    private Context context;
    private View rootView;
    private SearchView simpleSearchView;
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

    private void doMySearch(String query) {
        //todo make call to backendless and display as recycler view
        Toast.makeText(context, query, Toast.LENGTH_SHORT).show();
    }

    private void wireDaStuff() {
        RecyclerViewOnClick listener = new RecyclerViewOnClick() {
            @Override
            public void onClick(View v, int pos) {
                //todo make onclick
                Toast.makeText(context, "RecyclerViewOnClick", Toast.LENGTH_SHORT).show();
            }
        };
        results = new ArrayList<>();
        recyclerView = rootView.findViewById(R.id.search_results_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        searchResultsAdapter = new SearchResultsAdapter(results, context, listener);
        recyclerView.setAdapter(searchResultsAdapter);
        registerForContextMenu(recyclerView);
        simpleSearchView = (SearchView) rootView.findViewById(R.id.simpleSearchView); // inititate a search view
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doMySearch(query);
                return false;
            }

            @Override//
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}

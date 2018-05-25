package com.example.caroline.foodme.RecipeDisplay;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caroline.foodme.R;

import java.util.ArrayList;


public class IngredientDisplayFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ArrayList<String> ingredients;
    private View rootView;
    private TextView ingredientDisp;
    private Context context;
    private RecyclerView recyclerView;
    private IngredientDisplayAdapter adapter;
    private LinearLayoutManager layoutManager;


    public IngredientDisplayFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //* @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment IngredientDisplayFragment.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ingredients=getArguments().getStringArrayList("ingr");
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_ingredient_display, container, false);
        context = rootView.getContext();
        wireWidgets();
        return rootView;

    }

    private void wireWidgets() {

        ingredientDisp=rootView.findViewById(R.id.ingrdisplay);
        ingredientDisp.setText(ingredients.toString()+ingredients.size());
        recyclerView=rootView.findViewById(R.id.ingredient_display_recycler_view);
        adapter=new IngredientDisplayAdapter(ingredients, context);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        registerForContextMenu(recyclerView);
    }


}

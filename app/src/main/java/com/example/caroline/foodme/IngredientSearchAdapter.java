package com.example.caroline.foodme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by per6 on 3/27/18.
 */

public class IngredientSearchAdapter extends RecyclerView.Adapter<IngredientSearchAdapter.MyViewHolder> {
    private ArrayList<String> ingredients;
    private RecyclerViewOnClick click;
    private Context context;

    public IngredientSearchAdapter(ArrayList<String> ingredients, RecyclerViewOnClick click, Context context) {
        this.ingredients = ingredients;
        this.click = click;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_search_item, parent,false);
        return new MyViewHolder(view, click);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private EditText ingredientEdit;
        private TextView ingredientNumberTextView;
        private RecyclerViewOnClick recyclerViewClick;

        public MyViewHolder(View itemView, RecyclerViewOnClick click) {
            super(itemView);
            ingredientEdit= itemView.findViewById(R.id.ingredient_edittext);
            ingredientNumberTextView=itemView.findViewById(R.id.ingredient_textview);
            recyclerViewClick=click;
        }
    }
}

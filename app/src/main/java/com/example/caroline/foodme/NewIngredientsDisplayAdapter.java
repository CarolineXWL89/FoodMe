package com.example.caroline.foodme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by per6 on 3/27/18.
 */

public class NewIngredientsDisplayAdapter extends RecyclerView.Adapter<NewIngredientsDisplayAdapter.MyViewHolder> {


    private ArrayList<String> ingredients;
    private RecyclerViewOnClick click;
    private Context context;

    public NewIngredientsDisplayAdapter(ArrayList<String> ingredients, RecyclerViewOnClick click, Context context) {
        this.ingredients = ingredients;
        this.click = click;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.create_recipe_ingredient_add_display, parent,false);
        return new MyViewHolder(view, click);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.newIngredientsDisplay.setText(ingredients.get(position));
    }


    public ArrayList<String> getLisOfIngredients() {
        return ingredients;
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView newIngredientsDisplay;
        private RecyclerViewOnClick recyclerViewClick;

        public MyViewHolder(View itemView, RecyclerViewOnClick click) {
            super(itemView);
            newIngredientsDisplay = itemView.findViewById(R.id.newIngredientDisplay);
            recyclerViewClick = click;
        }

        @Override
        public void onClick(View v) {
            recyclerViewClick.onClick(v, getAdapterPosition());
        }

    }
}

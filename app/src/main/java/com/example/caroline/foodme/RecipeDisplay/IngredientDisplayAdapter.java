package com.example.caroline.foodme.RecipeDisplay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caroline.foodme.R;

import java.util.ArrayList;

/**
 * Created by Nicolo on 5/24/2018.
 */

public class IngredientDisplayAdapter extends RecyclerView.Adapter<IngredientDisplayAdapter.MyViewHolder>{
    private ArrayList<String> ingredients;
    private Context context;


    public IngredientDisplayAdapter(ArrayList<String> ingredients, Context context){
        this.ingredients=ingredients;
        this.context=context;
    }
    @Override
    public IngredientDisplayAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_display_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientDisplayAdapter.MyViewHolder holder, int position) {
        holder.ingredientTextView.setText(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ingredientTextView=itemView.findViewById(R.id.ingr_textview);

        }
    }
}

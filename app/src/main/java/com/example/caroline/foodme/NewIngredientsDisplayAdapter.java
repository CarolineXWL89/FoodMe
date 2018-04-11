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
    private Context context;

    public NewIngredientsDisplayAdapter(ArrayList<String> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.create_recipe_ingredient_add_display, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.newIngredientsDisplay.setText(ingredients.get(position));
        holder.newIngredientsDisplay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //yada
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ingredients.remove(position);
                ingredients.add(position, holder.newIngredientsDisplay.getText().toString());
                //notifyDataSetChanged();
            }
        });
    }

    public void removeItem(int position) {
        ingredients.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(String item, int position) {
        ingredients.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<String> getLisOfIngredients() {
        return ingredients;
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private EditText newIngredientsDisplay;

        public MyViewHolder(View itemView) {
            super(itemView);
            newIngredientsDisplay = itemView.findViewById(R.id.newIngredientDisplay);
        }

    }
}

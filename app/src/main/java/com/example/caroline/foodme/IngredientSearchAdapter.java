package com.example.caroline.foodme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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


    private static final String TAG = "IngredientSearchAdapter";
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
    public int getItemViewType(int position){
        return position;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.ingredientNumberTextView.setText("Ingredient #"+(position+1));

        holder.ingredientEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ingredients.remove(position);
                Log.d(TAG, "afterTextChanged: position+ "+position);
                ingredients.add(position, editable.toString());

                Log.d(TAG, "afterTextChanged: ingredinets in"+ ingredients.toString());

            }
        });


    }



    public ArrayList<String> getIngredients() {
        return ingredients;
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

package com.example.caroline.foodme;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by per6 on 3/27/18.
 */

public class NewIngredientsDisplayAdapter extends RecyclerView.Adapter<NewIngredientsDisplayAdapter.MyViewHolder> {

    private final String TAG = "TAG, you're it";
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
        Log.d(TAG, "onBindViewHolder: "+position);
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

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void removeItem(int position) {
        ingredients.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(String item, int position) {
        ingredients.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<String> getListOfIngredients() {
        return ingredients;
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private EditText newIngredientsDisplay;
        private ImageButton delete;

        public MyViewHolder(final View itemView) {
            super(itemView);
            newIngredientsDisplay = itemView.findViewById(R.id.newIngredientDisplay);
            delete = itemView.findViewById(R.id.deleteButton);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = ingredients.get(getAdapterPosition());

                    // backup of removed item for undo purpose
                    final String deletedItem = ingredients.get(getAdapterPosition());
                    final int deletedIndex = getAdapterPosition();

                    // remove the item from recycler view
                    removeItem(getAdapterPosition());

                    // showing snack bar with Undo option
                    Snackbar snackbar = Snackbar.make(itemView, name + " removed!", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            restoreItem(deletedItem, deletedIndex);
                        }
                    });
                    snackbar.show();
                }
            });
        }

    }
}

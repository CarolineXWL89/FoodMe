package com.example.caroline.foodme.SearchFragment;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecyclerViewOnClick;

import java.util.ArrayList;

/**
 * Created by Nicolo on 3/27/18.
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
        holder.ingredientText.setText(ingredients.get(position));
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView ingredientText;
        private TextView ingredientNumberTextView;
        private ImageButton deleteButton;

        public void removeItem(int position) {
            ingredients.remove(position);
            notifyItemRemoved(position);
        }

        public void restoreItem(String item, int position) {
            ingredients.add(position, item);
            notifyItemInserted(position);
        }

        public MyViewHolder( final View itemView, RecyclerViewOnClick click) {
            super(itemView);
            ingredientText = itemView.findViewById(R.id.ingredient_content_textview);
            ingredientNumberTextView = itemView.findViewById(R.id.ingredient_name_textview);
            deleteButton = itemView.findViewById(R.id.deleteButtonSearchFragment);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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

package com.example.caroline.foodme.FavoritesFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecipeNative;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by maylisw on 5/7/18.
 */
public class FavoritesDisplayAdapter extends RecyclerView.Adapter<FavoritesDisplayAdapter.MyViewHolder> {

    private final String TAG = "TAG, you're it";
    private ArrayList<DisplayerRecipe> favoritesList;
    private Context context;

    public FavoritesDisplayAdapter(ArrayList<DisplayerRecipe> favoritesList, Context context) {
        this.favoritesList = favoritesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorites_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.recipeTitle.setText(favoritesList.get(position).getName());
        Picasso.with(context).load(favoritesList.get(position).getImageURL()).fit().centerCrop().into(holder.recipeImage);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void removeItem(int position) {
        favoritesList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(DisplayerRecipe item, int position) {
        favoritesList.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<DisplayerRecipe> getListOfIngredients() {
        return favoritesList;
    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView recipeTitle;
        private ImageView recipeImage;

        public MyViewHolder(final View itemView) {
            super(itemView);
            recipeTitle = itemView.findViewById(R.id.favorites_recipe_title);
            recipeImage = itemView.findViewById(R.id.favorite_image_view);
            //todo on click load recipe
        }
    }
}

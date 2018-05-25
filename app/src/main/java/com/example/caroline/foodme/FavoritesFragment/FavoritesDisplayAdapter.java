package com.example.caroline.foodme.FavoritesFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.caroline.foodme.R;
import com.example.caroline.foodme.RecipeDisplay.RecipeDisplayActivity;
import com.example.caroline.foodme.RecipeNative;
import com.example.caroline.foodme.Search.SearchResultsActivity;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayerRecipe r = favoritesList.get(position);
                if(r.isBackendless()){
                    Backendless.Persistence.of(RecipeNative.class).findById(r.getId(), new AsyncCallback<RecipeNative>() {
                        @Override
                        public void handleResponse(RecipeNative response) {
                            Intent i = new Intent(context, RecipeDisplayActivity.class);
                            i.setType("RecipeNative");
                            i.putExtra("recipe_native_show", response);
                            context.startActivity(i);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "handleFault: "+ fault.getMessage());
                        }
                    });
                }
            }
        });
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
        }
    }
}

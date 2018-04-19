package com.example.caroline.foodme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by maylisw on 3/23/18.
 */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.MyViewHolder> {
        private RecyclerViewOnClick click;
        private List<Recipe> recipes;
        private Context context;
        public static final String TAG = "SearchResultsAdapter";
    public SearchResultsAdapter( List<Recipe> recipes, Context context, RecyclerViewOnClick click) {
            this.recipes = recipes;
            this.context = context;
            this.click = click;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.search_display, parent,false);
            return new MyViewHolder(view, click);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Recipe result = recipes.get(position);

            holder.description.setText(result.getRecipeName());
            holder.timeToPrepare.setText(result.getTimeNeeded());
           if(result.getImageURL() != null) {
                Picasso.with(context).load(result.getImageURL()).into(holder.image);
               Log.d(TAG, "onBindViewHolder: should have added pic:"+ result.getImageURL());
            } else {



               Picasso.with(context).load(R.drawable.ic_error_outline_black_24dp).into(holder.image);
           }
        }

        @Override
        public int getItemCount() {
            return recipes.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        {
            private RecyclerViewOnClick recyclerViewClick;
            private TextView description, timeToPrepare;
            private ImageView image;

            public MyViewHolder(View itemView, RecyclerViewOnClick click) {
                super(itemView);
                image = itemView.findViewById(R.id.food_picture);
                description = itemView.findViewById(R.id.recipe_description);
                timeToPrepare = itemView.findViewById(R.id.time_to_prepare);
                recyclerViewClick = click;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                recyclerViewClick.onClick(v, getAdapterPosition());
            }
        }

}

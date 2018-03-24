package com.example.caroline.foodme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by maylisw on 3/23/18.
 */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.MyViewHolder> {
        private RecyclerViewOnClick click;
        private List<Recipe> recipes;
        private Context context;
    public SearchResultsAdapter(List<Recipe> recipes, Context context, RecyclerViewOnClick click) {
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
  //          Picasso.with(context).load(result.getLink()).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return recipes.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        {
            private RecyclerViewOnClick recyclerViewClick;
            private TextView description;
            private ImageView image;

            public MyViewHolder(View itemView, RecyclerViewOnClick click) {
                super(itemView);
                image = itemView.findViewById(R.id.food_image_view);
                description = itemView.findViewById(R.id.recipe_description);
                recyclerViewClick = click;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                //todo do something
                recyclerViewClick.onClick(v, getAdapterPosition());
            }
        }

}

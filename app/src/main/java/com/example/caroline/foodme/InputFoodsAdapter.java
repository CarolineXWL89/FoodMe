package com.example.caroline.foodme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caroline.foodme.EdamamObjects.NutritionResponse;
import com.example.caroline.foodme.Search.SearchResultsAdapter;

/**
 * Created by princ on 08/05/2018.
 */

public class InputFoodsAdapter extends RecyclerView.Adapter<InputFoodsAdapter.MyViewHolder> {

    private NutritionResponse[] inputs;
    @Override
    public InputFoodsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_auto_generate_fragment, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InputFoodsAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView foodName, infoPlaceholder;
        private ImageView foodImage;

        public MyViewHolder(View itemView) {
            super(itemView);

            //wireWidgets
            foodName = itemView.findViewById(R.id.textView_food_name);
            foodImage = itemView.findViewById(R.id.imageView_food_pic);
            infoPlaceholder = itemView.findViewById(R.id.textView_info_placeholder);
        }

        //TODO set onClick to open to full nutrient page
    }
}

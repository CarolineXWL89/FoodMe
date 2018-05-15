package com.example.caroline.foodme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caroline.foodme.EdamamObjects.NutritionResponse;

import java.util.ArrayList;

/**
 * Created by princ on 08/05/2018.
 */

public class InputFoodsAdapter extends RecyclerView.Adapter<InputFoodsAdapter.MyViewHolder> {

    private ArrayList<NutritionResponse> inputs;
    private RecyclerViewOnClick onClick;
    public InputFoodsAdapter(ArrayList<NutritionResponse> inputs, RecyclerViewOnClick onClick){
        //basic constructor
        this.inputs = inputs;
        this.onClick = onClick;
    }

    @Override
    public InputFoodsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.auto_generate_fragment, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InputFoodsAdapter.MyViewHolder holder, int position) {
        NutritionResponse nutritionResponse = inputs.get(position);
        holder.foodName.setText(nutritionResponse.getIngredient().getParsed().getFood());
        holder.infoPlaceholder.setText(nutritionResponse.getHealthLabels().toString()); //TODO specify later
    }

    @Override
    public int getItemCount() {
        return inputs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView foodName, infoPlaceholder;
        private ImageView foodImage;
        private RecyclerViewOnClick click;

        public MyViewHolder(View itemView) {
            super(itemView);

            //wireWidgets
            foodName = itemView.findViewById(R.id.textView_food_name);
            foodImage = itemView.findViewById(R.id.imageView_food_pic);
            infoPlaceholder = itemView.findViewById(R.id.textView_info_placeholder);

            this.click = onClick;
        }


        //TODO set onClick to open to full nutrient page
    }
}

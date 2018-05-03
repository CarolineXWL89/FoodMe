package com.example.caroline.foodme.isThisUnused;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caroline.foodme.R;

import java.util.List;

/**
 * Created by michaelxiong on 3/13/18.
 * RecyclerView adapter
 */

//todo DELETE?

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    private List<Food> foods; //items in recycler view
    private Context context;

    /**
     *
     * @ param parent ViewGroup where view holder will be created ()
     * */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false); //sets view to be a food item view
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //fills in view w/ info
        Food food = foods.get(position);
        holder.foodNameView.setText(food.getFoodName());
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView foodNameView;
        private ImageView foodImageView;
        private CheckBox foodSelectBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            //wire widgets
            foodNameView = itemView.findViewById(R.id.food_name_view);
            foodImageView = itemView.findViewById(R.id.food_image_view);
            foodSelectBox = itemView.findViewById(R.id.food_select_box);

        }
    }

}

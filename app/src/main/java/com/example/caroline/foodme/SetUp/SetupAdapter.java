package com.example.caroline.foodme.SetUp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caroline.foodme.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelxiong on 3/13/18.
 * RecyclerView adapter
 */

public class SetupAdapter extends RecyclerView.Adapter<SetupAdapter.MyViewHolder> {

    private List<SetupItem> setupItems, selectedItems; //items in recycler view
    private Context context;
    private ItemSetUpActivity itemSetUpActivity;

    public SetupAdapter(List<SetupItem> setupItems, Context context, ItemSetUpActivity itemSetUpActivity) {
        this.setupItems = setupItems;
        this.context = context;
        this.itemSetUpActivity = itemSetUpActivity;
        selectedItems = new ArrayList<>();
    }

    public ArrayList<SetupItem> getSelectedItems(){
        return (ArrayList) selectedItems;
    }

    /**
     *
     * @ param parent ViewGroup where view holder will be created ()
     * */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.setup_item, parent, false); //sets view to be a food item view
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //fills in view w/ info
        final SetupItem setupItem = setupItems.get(position);
        holder.foodNameView.setText(setupItem.getFoodName());
        holder.foodImageView.setImageResource(setupItem.getFoodImage());
        holder.foodSelectBox.setChecked(false);
        for(int i = 0; i < selectedItems.size(); i++){
            if(selectedItems.get(i).getFoodName().equals(setupItem.getFoodName())){
                holder.foodSelectBox.setChecked(true);
            }
        }

        holder.foodSelectBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.d("onCheckedChanged", "checked");
                    selectedItems.add(setupItem);
                }
                if(!isChecked){
                    Log.d("onCheckedChanged", "unchecked");
                    selectedItems.remove(setupItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return setupItems.size();
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

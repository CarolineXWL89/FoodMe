package com.example.caroline.foodme.SetUp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caroline.foodme.R;

import java.util.List;

/**
 * Created by michaelxiong on 5/14/18.
 */

public class SetUpChecklistAdapter extends RecyclerView.Adapter<SetUpChecklistAdapter.MyViewHolder> {

    private List<SetUpChecklistItem> setUpChecklistItems;
    private Context context;
    private SetUpChecklistItem item;

    /**
     * @param setUpChecklistItems the list of preference activities that the user must fill out. Each setUpChecklistItem contains a name
     *                            and a variable describing if it has been completed.
     * @param context the context needed to make this adapter work.
     */
    public SetUpChecklistAdapter(List<SetUpChecklistItem> setUpChecklistItems, Context context) {
        this.setUpChecklistItems = setUpChecklistItems;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.setup_checklist_item, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        item = setUpChecklistItems.get(position);
        Log.d("onBindViewHolder",item.getItem());
        holder.textView.setText(item.getItem());

        //if the activity has been completed, there is a green checkmark next to its name. Otherwise there is an empty box.
        if(item.isCompleted()){
            holder.imageView.setImageResource(R.drawable.ic_check_black_24dp);
        }
        else{
            holder.imageView.setImageResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        Log.d("setUpChecklistItems", setUpChecklistItems.size()+"");
        return setUpChecklistItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;
        private ConstraintLayout constraintLayout;

        public MyViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.setup_checklist_textview);
            imageView = itemView.findViewById(R.id.setup_check_view);
            constraintLayout = itemView.findViewById(R.id.setup_checklist_constraintlayout);

            //Once the item is clicked, the user is sent to an Activity in which they choose their preferences.
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!item.isCompleted()) {
                        Intent i = new Intent(context, ItemSetUpActivity.class);
                        i.putExtra("SetUpTask", textView.getText().toString());
                        Log.d("SetUpTask", textView.getText().toString());
                        context.startActivity(i);
                    }
                    else{
                        Toast.makeText(context, "You have already completed this item", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!item.isCompleted()) {
                        Intent i = new Intent(context, ItemSetUpActivity.class);
                        i.putExtra("SetUpTask", textView.getText().toString());
                        Log.d("SetUpTask", textView.getText().toString());
                        context.startActivity(i);
                    }
                    else{
                        Toast.makeText(context, "You have already completed this item", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

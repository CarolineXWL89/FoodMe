package com.example.caroline.foodme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class CreateFragment extends Fragment {

    private View rootview;
    private ImageButton imageUpload;
    private EditText title, yield, timeNeeded, directions, newIngredient;
    private RecyclerView ingredients;
    private CardView createNewIngredient;

    public static final String TAG = "fragments";
    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_create, container, false);
        wireWidgets();
        return rootview;
    }

    private void wireWidgets() {
        //todo wire widgets
        imageUpload = rootview.findViewById(R.id.uploadImage);
        imageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo lauch image upload
            }
        });

        createNewIngredient = rootview.findViewById(R.id.addNew);
        createNewIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo create new ingredient
                //on enter adds to recylerview
            }
        });

    }

}

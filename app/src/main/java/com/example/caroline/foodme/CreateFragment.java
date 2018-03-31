package com.example.caroline.foodme;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateFragment extends Fragment {

    private View rootview;
    private ImageButton imageUpload;
    private EditText title, yield, timeNeeded, directions, newIngredient;
    private RecyclerView ingredientsRecylerView;
    private ImageButton createNewIngredient;
    private ArrayList<String> ingredients;
    private Context context;
    private LinearLayoutManager layoutManager;
    private NewIngredientsDisplayAdapter newIngredientsDisplayAdapter;
    private Button submit;

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
    }//

    private void wireWidgets() {
        ingredients = new ArrayList<>();
        context = getActivity();
        imageUpload = rootview.findViewById(R.id.uploadImage);
        imageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo lauch image upload
            }
        });

        createNewIngredient = rootview.findViewById(R.id.addIngredient);
        newIngredient = rootview.findViewById(R.id.newIngredient);
        createNewIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo create new ingredient
                //on enter adds to recylerview
                String result = newIngredient.getText().toString();
                if(!(result.equals(" ") || result.equals(""))) {
                    ingredients.add(result);
                } else {
                    Toast.makeText(context, "Please type ingredient", Toast.LENGTH_LONG).show();
                }
            }
        });

        title = rootview.findViewById(R.id.recipeTitleEditText);
        yield = rootview.findViewById(R.id.yieldEditText);
        timeNeeded = rootview.findViewById(R.id.timeEditText);
        directions = rootview.findViewById(R.id.directions);
        ingredientsRecylerView = rootview.findViewById(R.id.ingrediantsRecylerView);
        layoutManager = new LinearLayoutManager(getActivity());
        ingredientsRecylerView.setLayoutManager(layoutManager);
        ingredientsRecylerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerViewOnClick listener = new RecyclerViewOnClick() {
            @Override
            public void onClick(View v, int pos) {
                //todo make onclick
                //todo edit ingredient --> create alert dialogue OK updates text
                Toast.makeText(context, "UPDAT W/ alert dialogue here", Toast.LENGTH_LONG).show();
            }
        };
        newIngredientsDisplayAdapter = new NewIngredientsDisplayAdapter(ingredients, listener, context);
        ingredientsRecylerView.setAdapter(newIngredientsDisplayAdapter);
        registerForContextMenu(ingredientsRecylerView);
        submit = rootview.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo upload to backendless
            }
        });

    }

}

package com.example.caroline.foodme;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateFragment extends Fragment  {

    private View rootview;
    private ImageButton imageUpload;
    private EditText title, yield, timeNeeded, directions, newIngredient;
    private RecyclerView ingredientsRecylerView;
    private ImageButton createNewIngredient;
    private ArrayList<String> ingredients;
    private Context context;
    private LinearLayoutManager layoutManager;
    private NewIngredientsDisplayAdapter newIngredientsDisplayAdapter;
    private Button submit, clear;
    private String picUrl;

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
        ingredients = new ArrayList<>();
        ingredients.add(" ");
        context = getActivity();

        //wires image uploader
        imageUpload = rootview.findViewById(R.id.uploadImage);
        imageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Upload Pic", Toast.LENGTH_SHORT).show();
                //todo UPLOAD
                //todo if uploaded show image
                //todo set url to be uploaded
            }
        });

        //wires text box for new ingredient
        createNewIngredient = rootview.findViewById(R.id.addIngredient);
        createNewIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = newIngredient.getText().toString();
                if (!(result.equals(" ") || result.equals(""))) {
                    ingredients.add(result);
                    newIngredientsDisplayAdapter.notifyDataSetChanged();
                    newIngredient.setText("");
                } else {
                    Toast.makeText(context, "Please type ingredient", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Wires edit texts
        newIngredient = rootview.findViewById(R.id.newIngredient);
        title = rootview.findViewById(R.id.recipeTitleEditText);
        yield = rootview.findViewById(R.id.yieldEditText);
        timeNeeded = rootview.findViewById(R.id.timeEditText);
        directions = rootview.findViewById(R.id.directions);

        //wires recyvler view and adds adapters
        ingredientsRecylerView = rootview.findViewById(R.id.ingrediantsRecylerView);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        ingredientsRecylerView.setLayoutManager(layoutManager);
        ingredientsRecylerView.setItemAnimator(new DefaultItemAnimator());
        newIngredientsDisplayAdapter = new NewIngredientsDisplayAdapter(ingredients, context);
        ingredientsRecylerView.setAdapter(newIngredientsDisplayAdapter);
        registerForContextMenu(ingredientsRecylerView);

        //wires submit button
        submit = rootview.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe recipe = new Recipe();
                //todo upload to backendless
            }
        });

        clear = rootview.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("");
                yield.setText("");
                timeNeeded.setText("");
                directions.setText("");
                ingredients.clear();
                picUrl = "";
            }
        });

        //deletes placeholder ingredient
        newIngredientsDisplayAdapter.removeItem(0);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.pictureUrl), picUrl);
        outState.putString(getString(R.string.recipeTitle), title.getText().toString());
        outState.putString(getString(R.string.servingSize), yield.getText().toString());
        outState.putString(getString(R.string.timeNeeded), timeNeeded.getText().toString());
        outState.putStringArrayList(getString(R.string.ingredients), ingredients);
        outState.putString(getString(R.string.directions), directions.getText().toString());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            picUrl = savedInstanceState.getString(getString(R.string.pictureUrl), "");
            title.setText(savedInstanceState.getString(getString(R.string.recipeTitle), ""));
            yield.setText(savedInstanceState.getString(getString(R.string.servingSize), ""));
            timeNeeded.setText(savedInstanceState.getString(getString(R.string.timeNeeded), ""));
            ingredients = savedInstanceState.getStringArrayList(getString(R.string.ingredients));
            directions.setText(savedInstanceState.getString(getString(R.string.directions)));
        }

    }
}


/*
HOW TO IMPLEMENT SWIPE TO DELETE

MAIN CLASS:  (implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener)
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
    new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(/*Recycler view*//*);

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof /*Adapter type*//*.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = /*Array List*//*.get(viewHolder.getAdapterPosition());

            // backup of removed item for undo purpose
            final String deletedItem = /*Array List*//*.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            /*Adapter*//*.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar.make(/*view*//*, name + " removed!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Adapter*//*.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.show();
        }
    }
ADAPTER CLASS:

    public void removeItem(int position) {
        ingredients.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(String item, int position) {
        ingredients.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<String> getListOfIngredients() {
        return ingredients;
    }

* */
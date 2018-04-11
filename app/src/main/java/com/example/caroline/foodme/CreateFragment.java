package com.example.caroline.foodme;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateFragment extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

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
    }//todo two tables for recycler view
    //todo delte swipping
    //todo fix problem
    //todo automatic delete intail ingrediatn
    //todo delte button in card view

    private void wireWidgets() {
        ingredients = new ArrayList<>();
        ingredients.add(" ");
        context = getActivity();
        imageUpload = rootview.findViewById(R.id.uploadImage);
        imageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Upload Pic", Toast.LENGTH_SHORT).show();
                //todo UPLOAD
            }
        });

        createNewIngredient = rootview.findViewById(R.id.addIngredient);
        createNewIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = newIngredient.getText().toString();
                if(!(result.equals(" ") || result.equals(""))) {
                    ingredients.add(result);
                    newIngredientsDisplayAdapter.notifyDataSetChanged();
                    newIngredient.setText("");
                } else {
                    Toast.makeText(context, "Please type ingredient", Toast.LENGTH_LONG).show();
                }
            }
        });
        newIngredient = rootview.findViewById(R.id.newIngredient);
        title = rootview.findViewById(R.id.recipeTitleEditText);
        yield = rootview.findViewById(R.id.yieldEditText);
        timeNeeded = rootview.findViewById(R.id.timeEditText);
        directions = rootview.findViewById(R.id.directions);
        ingredientsRecylerView = rootview.findViewById(R.id.ingrediantsRecylerView);
        layoutManager = new LinearLayoutManager(getActivity());
        ingredientsRecylerView.setLayoutManager(layoutManager);
        ingredientsRecylerView.setItemAnimator(new DefaultItemAnimator());
        newIngredientsDisplayAdapter = new NewIngredientsDisplayAdapter(ingredients, context);
        ingredientsRecylerView.setAdapter(newIngredientsDisplayAdapter);
        registerForContextMenu(ingredientsRecylerView);
        submit = rootview.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo upload to backendless
            }
        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(ingredientsRecylerView);
        //todo tell user that swipping from left will delete item
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof NewIngredientsDisplayAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = ingredients.get(viewHolder.getAdapterPosition());

            // backup of removed item for undo purpose
            final String deletedItem = ingredients.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            newIngredientsDisplayAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar.make(rootview, name + " removed!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newIngredientsDisplayAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.show();
        }
    }
}

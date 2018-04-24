package com.example.caroline.foodme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

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
    private ImageUploadClicker imageUploadClicker;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 123;

    public static final String TAG = "fragments";
    public CreateFragment() {
        // Required empty public constructor
    }

    //todo CLEAN UP CODE
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
        imageUploadClicker = new ImageUploadClicker(getContext(), getActivity(), this);
        imageUpload.setOnClickListener(imageUploadClicker);

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
        submit.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Recipe recipe = checkText();
                if(recipe != null){
                    Backendless.Data.of(Recipe.class).save(recipe, new AsyncCallback<Recipe>() {
                        @Override
                        public void handleResponse(Recipe response) {
                            Toast.makeText(context, "Success, "+ recipe.getRecipeName()+ " has been uploaded", Toast.LENGTH_SHORT).show();
                            title.setText("");
                            yield.setText("");
                            timeNeeded.setText("");
                            directions.setText("");
                            ingredients.clear();
                            picUrl = "";
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(context, "Recipe cannot be uploaded right now, please try again later", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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

    private Recipe checkText() {
        Recipe recipe = new Recipe();
        String titleText = title.getText().toString();
        String directionsText = directions.getText().toString();
        String yieldText = yield.getText().toString();
        String timeText = timeNeeded.getText().toString();
        String[] message = new String[5];
        if(isOk(titleText)){
            recipe.setRecipeName(titleText);
        } else {
            message[0] = "Please add a recipe name";
        }

        if(isOk(directionsText)){
            recipe.setDirections(directionsText);
        } else {
            message[1] = "Please add directions";
        }

        if(isOk(yieldText)){
            recipe.setServings(yieldText);
        } else {
            message[2] = "Please add serving size";
        }

        if(isOk(timeText)){
            recipe.setTimeNeeded(timeText);
        } else {
            message[3] = "Please add a time";
        }

        if(ingredients.size() > 0){
            recipe.setIngredients(ingredients.toString());
        } else {
            message[4] = "Please add ingredients";
        }


        if(notEmpty(message)){
            return null;
        } else {
            return recipe;
        }
    }

    private boolean notEmpty(String[] message) {
        boolean value = true;
        String toast = "";
        for(int i = 0; i < message.length; i++){
            if(!isOk(message[i])){
                value = false;
                toast = toast + message[i] + ", ";
            }
        }

        if(isOk(toast) && !value){
            toast.substring(0, toast.length() - 2);
            Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return value;
        }
    }

    private boolean isOk(String titleText) {
        if(titleText.equals("")){
            return false;
        } else if(titleText.equals(" ")){
            return false;
        } else if(titleText.equals("  ")){
            return false;
        } else{
            return true;
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: IM CALLED");
        Toast.makeText(context,"IM CALLED",Toast.LENGTH_SHORT).show();
        if(requestCode ==  MY_PERMISSIONS_REQUEST_CAMERA) {
            // If request is cancelled, the result arrays are empty.
            //if results array is not empty then we can use the camera

            Log.d(TAG, "onRequestPermissionsResult: IM CALLED2");
            Toast.makeText(context,"IM CALLED2",Toast.LENGTH_SHORT).show();
            imageUploadClicker.setCanUseCamera(grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED);
        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageUpload.setImageBitmap(imageBitmap);
            //todo upload to imagur and save to image url here
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
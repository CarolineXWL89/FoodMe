package com.example.caroline.foodme.SetUp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.caroline.foodme.BackendlessSettings;
import com.example.caroline.foodme.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ItemSetUpActivity extends AppCompatActivity {

    private TextView titleText;
    private MultiAutoCompleteTextView searchBar;
    private RecyclerView recyclerView;
    private Button doneButton, searchButton, endSearchButton;
    private String setUpTask;
    private SharedPreferences sharedPref;
    private List<SetupItem> setupItems;
    private ArrayList<String> setupItemNames;
    private RecyclerView.LayoutManager layoutManager;
    private SetupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_set_up);
        Backendless.initApp(this, BackendlessSettings.APP_ID, BackendlessSettings.API_KEY);
        Intent i = getIntent();
        sharedPref = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        setUpTask = i.getStringExtra("SetUpTask");
        wireWidgets();
        setUpRecyclerView();
        setUpSearchBar();
    }

    public void wireWidgets(){
        titleText = findViewById(R.id.item_setup_title_textview);
        titleText.setText(setUpTask);
        searchBar = findViewById(R.id.item_setup_multiautocompletetextview);
        recyclerView = findViewById(R.id.item_setup_recyclerview);
        doneButton = findViewById(R.id.item_setup_done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: save data in Backendless
                final String setUpItem = setUpTask.substring(0, setUpTask.indexOf('P') -1);
                String userUserName = sharedPref.getString("userUserName", "null");
                String userPassword = sharedPref.getString("userPassword", "null");
                Backendless.UserService.login(userUserName, userPassword, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        response.setProperty(setUpItem.toLowerCase()+"preferences", adapter.getSelectedItems().toString());
                        Backendless.Data.save(response, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                Log.d("Data save", "success!");
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(ItemSetUpActivity.this, "Setup not completed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(ItemSetUpActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                Log.d("SelectedItems", adapter.getSelectedItems().toString());
                SharedPreferences.Editor edit = sharedPref.edit();
                edit.remove(setUpItem+"Setup");
                edit.putInt(setUpItem+"Setup", 1);
                edit.commit();
//                reset();
                Intent i = new Intent(ItemSetUpActivity.this, AccountSetUpActivity.class);
                startActivity(i);
            }
        });
        searchButton = findViewById(R.id.item_setup_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> searchedStrings = new ArrayList<>();
                String searched = searchBar.getText().toString();
                while (searched.contains(",")) {
                    Log.d("searched", "found a searchitem: " + searched.substring(0, searched.indexOf(',')));
                    searchedStrings.add(searched.substring(0, searched.indexOf(',')));
                    searched = searched.substring(searched.indexOf(",") + 2);
                }
                if(searched.length() != 0){
                    Log.d("searched", "found a searchitem: " + searched);
                    searchedStrings.add(searched);
                }
                ArrayList<SetupItem> searchedItems = new ArrayList<>();
                for(int i = 0; i < setupItems.size(); i++){
                    for(int j = 0; j<searchedStrings.size(); j++){
                        if(setupItems.get(i).getFoodName().toLowerCase().equals(searchedStrings.get(j).toLowerCase())){
                            Log.d("searchedItem added", setupItems.get(i).getFoodName());
                            searchedItems.add(setupItems.get(i));
                        }
                    }
                }
                if(!(searchedItems.equals(null)||searchedItems.size()==0)){
                    Log.d("onSearchClick", "Changing the recyclerview");
                    adapter = new SetupAdapter(searchedItems, ItemSetUpActivity.this, ItemSetUpActivity.this);
                    recyclerView.setAdapter(adapter);
                    endSearchButton.setVisibility(View.VISIBLE);
                    endSearchButton.setClickable(true);
                }
                else{
                    Toast.makeText(ItemSetUpActivity.this, "Items not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId != 0 || event.getAction() == KeyEvent.ACTION_DOWN) {
                    Log.d("onEditorAction", "enter clicked");
                    ArrayList<String> searchedStrings = new ArrayList<>();
                    String searched = searchBar.getText().toString();
                    while (searched.contains(",")) {
                        Log.d("searched", "found a searchitem: " + searched.substring(0, searched.indexOf(',')));
                        searchedStrings.add(searched.substring(0, searched.indexOf(',')));
                        searched = searched.substring(searched.indexOf(",") + 2);
                    }
                    if(searched.length() != 0){
                        Log.d("searched", "found a searchitem: " + searched);
                        searchedStrings.add(searched);
                    }
                    ArrayList<SetupItem> searchedItems = new ArrayList<>();
                    for(int i = 0; i < setupItems.size(); i++){
                        for(int j = 0; j<searchedStrings.size(); j++){
                            if(setupItems.get(i).getFoodName().toLowerCase().equals(searchedStrings.get(j).toLowerCase())){
                                Log.d("searchedItem added", setupItems.get(i).getFoodName());
                                searchedItems.add(setupItems.get(i));
                            }
                        }
                    }
                    if(!(searchedItems.equals(null)||searchedItems.size()==0)){
                        Log.d("onSearchClick", "Changing the recyclerview");
                        adapter = new SetupAdapter(searchedItems, ItemSetUpActivity.this, ItemSetUpActivity.this);
                        recyclerView.setAdapter(adapter);
                        endSearchButton.setVisibility(View.VISIBLE);
                        endSearchButton.setClickable(true);
                    }
                    else{
                        Toast.makeText(ItemSetUpActivity.this, "Items not found", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                } else {
                    Log.d("onEditorAction", "other button clicked");
                    return false;
                }
            }
        });

        endSearchButton = findViewById(R.id.item_setup_end_search_button);
        endSearchButton.setVisibility(View.GONE);
        endSearchButton.setClickable(false);
        endSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new SetupAdapter(setupItems, ItemSetUpActivity.this, ItemSetUpActivity.this);
                recyclerView.setAdapter(adapter);
                endSearchButton.setVisibility(View.GONE);
                endSearchButton.setClickable(false);
            }
        });

    }

    public void setUpRecyclerView(){
        setupItems = new ArrayList<SetupItem>();
        setupItemNames = new ArrayList<String>();
        if(setUpTask.equals("Cuisine Preferences")){
            setupItems.add(new SetupItem("American", R.drawable.american_flag));
            setupItems.add(new SetupItem("British", R.drawable.british_flag));
            setupItems.add(new SetupItem("Chinese", R.drawable.chinese_flag));
            setupItems.add(new SetupItem("French", R.drawable.french_flag));
            setupItems.add(new SetupItem("Indian", R.drawable.indian_flag));
            setupItems.add(new SetupItem("Italian", R.drawable.italian_flag));
            setupItems.add(new SetupItem("Japanese", R.drawable.japanese_flag));
            setupItems.add(new SetupItem("Korean", R.drawable.korean_flag));
            setupItems.add(new SetupItem("Mexican", R.drawable.mexican_flag));
            setupItems.add(new SetupItem("Spanish", R.drawable.spainish_flag));
            setupItems.add(new SetupItem("Thai", R.drawable.thai_flag));
        }
        if(setUpTask.equals("Ingredient Preferences")){
            ArrayList<String> carbItems = readFromFile(this, R.raw.carbohydrates);
            for(int i = 0; i < carbItems.size(); i++){
                String newItemName = carbItems.get(i).substring(0,1).toUpperCase() + carbItems.get(i).substring(1);
                setupItems.add(new SetupItem(newItemName, R.drawable.carb_icon));
            }
            ArrayList<String> fruitItems = readFromFile(this, R.raw.fruits);
            for(int i = 0; i < fruitItems.size(); i++){
                String newItemName = fruitItems.get(i).substring(0,1).toUpperCase() + fruitItems.get(i).substring(1);
                setupItems.add(new SetupItem(newItemName, R.drawable.fruit_icon));
            }
            ArrayList<String> oilItems = readFromFile(this, R.raw.oils);
            for(int i = 0; i < oilItems.size(); i++){
                String newItemName = oilItems.get(i).substring(0,1).toUpperCase() + oilItems.get(i).substring(1);
                setupItems.add(new SetupItem(newItemName, R.drawable.oil_icon));
            }
            ArrayList<String> proteinItems = readFromFile(this, R.raw.proteins);
            for(int i = 0; i < proteinItems.size(); i++){
                String newItemName = proteinItems.get(i).substring(0,1).toUpperCase() + proteinItems.get(i).substring(1);
                setupItems.add(new SetupItem(newItemName, R.drawable.protein_icon));
            }
            ArrayList<String> sauceItems = readFromFile(this, R.raw.sauces);
            for(int i = 0; i < sauceItems.size(); i++){
                String newItemName = sauceItems.get(i).substring(0,1).toUpperCase() + sauceItems.get(i).substring(1);
                setupItems.add(new SetupItem(newItemName, R.drawable.sauce_icon));
            }
            ArrayList<String> spiceItems = readFromFile(this, R.raw.spices);
            for(int i = 0; i < spiceItems.size(); i++){
                String newItemName = spiceItems.get(i).substring(0,1).toUpperCase() + spiceItems.get(i).substring(1);
                setupItems.add(new SetupItem(newItemName, R.drawable.spice_icon));
            }
            ArrayList<String> vegeItems = readFromFile(this, R.raw.vegetables);
            for(int i = 0; i < vegeItems.size(); i++){
                String newItemName = vegeItems.get(i).substring(0,1).toUpperCase() + vegeItems.get(i).substring(1);
                setupItems.add(new SetupItem(newItemName, R.drawable.vege_icon));
            }
            setImages();
        }
        if(setUpTask.equals("Diet Preferences")){
            setupItems.add(new SetupItem("Vegan", R.drawable.vegan_icon));
            setupItems.add(new SetupItem("ovo-Vegetarian", R.drawable.ovo_vegetarian_icon));
            setupItems.add(new SetupItem("lacto-Vegetarian", R.drawable.lacto_vegetarian_icon));
         //   setupItems.add(new SetupItem("ovo-lacto-Vegetarian", R.drawable.ovo_lacto_vegetarian_icon));
            setupItems.add(new SetupItem("Pescatarian", R.drawable.pescatarian_icon));
            setupItems.add(new SetupItem("Omnivore", R.drawable.protein_icon));
            //TODO: fill this list out more
        }
        for(int i = 0; i < setupItems.size(); i++){
            setupItemNames.add(setupItems.get(i).getFoodName());
        }
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SetupAdapter(setupItems, this, this);
        recyclerView.setAdapter(adapter);

    }

    public void setUpSearchBar(){
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_item, setupItemNames);
        searchBar.setAdapter(mAdapter);
        searchBar.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private ArrayList<String> readFromFile(Context context, int id) {

        ArrayList<String> list = new ArrayList<String>();

        try {
            InputStream inputStream = context.getResources().openRawResource(id);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    list.add(receiveString);
                }

                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return list;
    }

    public void setImages(){

    }


    public void reset(){
        SharedPreferences.Editor edit = sharedPref.edit();
        String setUpItem = setUpTask.substring(0, setUpTask.indexOf('P') -1);
        edit.remove(setUpItem+"Setup");
        edit.putInt(setUpItem+"Setup", 0);
        edit.commit();
    }
}

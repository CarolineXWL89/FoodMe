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
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.caroline.foodme.BackendlessSettings;
import com.example.caroline.foodme.HomePageActivity;
import com.example.caroline.foodme.R;
import com.example.caroline.foodme.UserInfo.AccountSettingsActivity;

import java.util.ArrayList;

public class AccountSetUpActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button finishButton;
    private ProgressBar progressBar;
    private SetUpChecklistAdapter adapter;
    private ArrayList<SetUpChecklistItem> setupItems;
    private SharedPreferences sharedPref;
    private int partsCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_set_up);
        Backendless.initApp(this, BackendlessSettings.APP_ID, BackendlessSettings.API_KEY);
        sharedPref = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        if(sharedPref.getInt("CuisineSetup", -1) == -1){
            edit.putInt("CuisineSetup", 0);
            edit.putInt("IngredientSetup", 0);
            edit.putInt("DietSetup", 0);
            edit.commit();
        }
        partsCompleted = 0;
        setupItems = new ArrayList<>();
        setupItemsCreate();
        wireWidgets();
    }

    public void wireWidgets(){
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SetUpChecklistAdapter(setupItems, this);
        finishButton = findViewById(R.id.setup_finish_button);
        recyclerView.setAdapter(adapter);
        progressBar = findViewById(R.id.setup_progressbar);
        progressBar.setProgress((partsCompleted*100)/3);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(partsCompleted == 3){
                    //TODO: open HomePageActivity, but first, change the thingy in Backendless
                    String userUserName = sharedPref.getString("userUserName", "null");
                    String userPassword = sharedPref.getString("userPassword", "null");
                    Backendless.UserService.login(userUserName, userPassword, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            response.setProperty("updatedsetup", true);
                            Backendless.Data.save(response, new AsyncCallback<BackendlessUser>() {
                                @Override
                                public void handleResponse(BackendlessUser response) {
                                    Log.d("Data save", "success!");
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(AccountSetUpActivity.this, "Setup not completed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(AccountSetUpActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    Intent i = new Intent(AccountSetUpActivity.this, HomePageActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(AccountSetUpActivity.this, "Setup not completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setupItemsCreate(){
        if (sharedPref.getInt("CuisineSetup", -1) == 0){
            setupItems.add(new SetUpChecklistItem("Cuisine Preferences", false));
        }
        else if(sharedPref.getInt("CuisineSetup", -1)==1){
            partsCompleted += 1;
            setupItems.add(new SetUpChecklistItem("Cuisine Preferences", true));
        }
        if (sharedPref.getInt("IngredientSetup", -1) == 0){
            setupItems.add(new SetUpChecklistItem("Ingredient Preferences", false));
        }
        else if(sharedPref.getInt("IngredientSetup", -1)==1){
            partsCompleted += 1;
            setupItems.add(new SetUpChecklistItem("Ingredient Preferences", true));
        }
        if (sharedPref.getInt("DietSetup", -1) == 0){
            setupItems.add(new SetUpChecklistItem("Diet Preferences", false));
        }
        else if(sharedPref.getInt("DietSetup", -1)==1){
            partsCompleted += 1;
            setupItems.add(new SetUpChecklistItem("Diet Preferences", true));
        }
    }

}

package com.example.caroline.foodme.SetUp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

public class ItemSetUpActivity extends AppCompatActivity {

    private TextView titleText;
    private MultiAutoCompleteTextView searchBar;
    private RecyclerView recyclerView;
    private Button doneButton;
    private String setUpTask;
    private SharedPreferences sharedPref;
    private List<SetupItem> setupItems;
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
    }

    public void setUpRecyclerView(){
        setupItems = new ArrayList<SetupItem>();
        if(setUpTask.equals("Cuisine Preferences")){
            setupItems.add(new SetupItem("American", "https://spoonacular.com/application/frontend/images/academy/American-cuisine.jpg"));
            setupItems.add(new SetupItem("British", "https://www.telegraph.co.uk/content/dam/onlyinbritain/british-food-full-english-breakfast.jpg"));
            setupItems.add(new SetupItem("Chinese", "https://img.huffingtonpost.com/asset/585be1aa1600002400bdf2a6.jpeg?ops=scalefit_970_noupscale"));
            setupItems.add(new SetupItem("French", "http://www.internetbusinessideas-viralmarketing.com/images/French-Cuisine5.jpg"));
            setupItems.add(new SetupItem("Indian", "https://img.etimg.com/thumb/msid-62225487,width-643,imgsize-110440,resizemode-4/indian-cuisine-.jpg"));
            setupItems.add(new SetupItem("Italian", "https://dynamicmedia.zuza.com/zz/m/original_/b/8/b8c5b28e-8af2-4d76-bc77-b862789635d5/IYN1_SS___Gallery.jpg"));
            setupItems.add(new SetupItem("Japanese", "https://asiatourist.co/wp-content/uploads/2017/09/Japanese-cuisine-0.jpg"));
            setupItems.add(new SetupItem("Jewish", "https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_1439,w_2560,x_0,y_0/dpr_2.0/c_limit,w_740/fl_lossy,q_auto/v1492114997/articles/2016/03/19/how-nyc-s-jewish-food-scene-got-hip/160318-rothbaum-jewish-food-tease_amdocx"));
            setupItems.add(new SetupItem("Korean", "http://www.englishspectrum.com/wp-content/uploads/2017/01/Insadong-Bimbibap.jpg"));
            setupItems.add(new SetupItem("Mexican", "http://cancunmexicangrillon33rd.com/files/2014/04/002.jpg"));
            setupItems.add(new SetupItem("Spanish", "http://www.hoteliermiddleeast.com/pictures/2017/El-Sur.jpg"));
            setupItems.add(new SetupItem("Thai", "https://i2.wp.com/www.tatnews.org/wp-content/uploads/2018/01/Discover-Thai-Cuisine-through-its-famous-four-regions-Phat-Thai.jpg?ssl=1"));
        }
        if(setUpTask.equals("Ingredient Preferences")){
            setupItems.add(new SetupItem("Potato", "https://cdn0.woolworths.media/content/wowproductimages/large/124831.jpg"));
            //TODO: figure out how to add a bunch of ingredients from the raw files, and then use the datamuserecipeasearch to get image urls
        }
        if(setUpTask.equals("Diet Preferences")){
            setupItems.add(new SetupItem("Vegan", "https://wellandfull.com/wp-content/uploads/2016/02/WellandFull-1-23.jpg"));
            //TODO: fill this list out more
        }
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new SetupAdapter(setupItems, this, this);
        recyclerView.setAdapter(adapter);

    }


    public void reset(){
        SharedPreferences.Editor edit = sharedPref.edit();
        String setUpItem = setUpTask.substring(0, setUpTask.indexOf('P') -1);
        edit.remove(setUpItem+"Setup");
        edit.putInt(setUpItem+"Setup", 0);
        edit.commit();
    }
}

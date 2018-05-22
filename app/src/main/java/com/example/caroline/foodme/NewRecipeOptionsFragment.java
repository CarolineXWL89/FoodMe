package com.example.caroline.foodme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.caroline.foodme.CreateFragment.CreateFragment;
import com.example.caroline.foodme.GenerateFragment.AutoGenerateFragment;

/**
 * Created by princ on 11/05/2018.
 */

public class NewRecipeOptionsFragment extends Fragment {

    private View rootView;
    private Button createNewButton, generatorButton;
    private Context context = getActivity();

    public NewRecipeOptionsFragment(){
        //required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.new_recipe_option_fragment, container, false);
        wireWidgets();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void wireWidgets(){
        //wire widgets
        createNewButton = rootView.findViewById(R.id.button_create_new);
        generatorButton = rootView.findViewById(R.id.button_generate_new);

        //sets onClickListeners
        createNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO send to create fragment
                /*Intent i = new Intent(getActivity(), CreateFragment.class);
                startActivity(i);*/
                /*
                *FragmentManager fm = getFragmentManager();
                *FragmentTransaction ft = fm.beginTransaction();
                *FragmentGreen llf = new FragmentGreen();
                *ft.replace(R.id.listFragment, llf);
                *ft.commit();
                 */

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CreateFragment createFragment = new CreateFragment();
                fragmentTransaction.replace(R.id.container_fragment, createFragment);
                createNewButton.setVisibility(View.INVISIBLE);
                generatorButton.setVisibility(View.INVISIBLE);
                fragmentTransaction.commit();
            }
        });

        generatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO send to generate fragment
                /*Intent i = new Intent(getActivity(), AutoGenerateFragment.class);
                startActivity(i);*/
                Log.d("getActivity context", getActivity().getLocalClassName());
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AutoGenerateFragment autoGenerateFragment = new AutoGenerateFragment();
                fragmentTransaction.replace(R.id.container_fragment, autoGenerateFragment);
                fragmentTransaction.commit();
            }
        });


    }

    @Override
    public Context getContext() {
        return context;
    }
}

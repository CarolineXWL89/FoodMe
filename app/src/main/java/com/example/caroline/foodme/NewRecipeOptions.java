package com.example.caroline.foodme;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by princ on 11/05/2018.
 */

public class NewRecipeOptions extends Fragment {

    private View rootView;
    Button createNewButton, generatorButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.new_recipe_option_fragment, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wireWidgets();
    }

    private void wireWidgets(){
        //wire widgets
        createNewButton = rootView.findViewById(R.id.button_create_new);
        generatorButton = rootView.findViewById(R.id.button_generate_new);

        //sets onClickListeners

    }
}

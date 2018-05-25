package com.example.caroline.foodme.RecipeDisplay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caroline.foodme.R;


public class ProcedureDisplayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PROCEDURE = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View rootView;

    // TODO: Rename and change types of parameters
    private String procedure;
    private TextView procedureDisplay;
    private String mParam2;

  //  private OnFragmentInteractionListener mListener;

    public ProcedureDisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        procedure="";
        if (getArguments() != null) {
            procedure=getArguments().getString("procedure");
            //mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private void wireWidgets() {
        procedureDisplay= rootView.findViewById(R.id.procedure_textview);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_procedure_display, container, false);
        wireWidgets();
        setText();
        return rootView;
    }

    private void setText() {
        procedureDisplay.setText(procedure);
    }

}

package com.example.caroline.foodme.UserInfo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.caroline.foodme.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AccountPreferenceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AccountPreferenceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountPreferenceFragment extends PreferenceFragment {

    public AccountPreferenceFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        addPreferencesFromResource(R.xml.pref_account);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}


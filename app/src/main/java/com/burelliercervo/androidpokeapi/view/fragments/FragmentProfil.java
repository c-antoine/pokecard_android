package com.burelliercervo.androidpokeapi.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burelliercervo.androidpokeapi.R;

/**
 * Created by iem on 02/03/2018.
 */

public class FragmentProfil extends BaseFragment {
    public static FragmentProfil instanceFragment;

    public static FragmentProfil getInstanceFragment() {
        if (instanceFragment == null) {
            instanceFragment = new FragmentProfil();
        }
        return instanceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void iniComponent(){

    }
}

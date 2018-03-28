package com.burelliercervo.androidpokeapi.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {
    public BaseActivity context;


    public void changeFragment(BaseFragment f) {
        context.changeFragment(f);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = (MainActivity) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (MainActivity)context;
    }
}

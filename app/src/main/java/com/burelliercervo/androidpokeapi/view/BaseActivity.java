package com.burelliercervo.androidpokeapi.view;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.burelliercervo.androidpokeapi.R;

public abstract class BaseActivity extends AppCompatActivity {

    public void changeFragment(BaseFragment f) {
        getSupportFragmentManager().beginTransaction().
//                setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
//                        R.anim.enter_from_left, R.anim.exit_to_right).
                replace(R.id.content, f).
                addToBackStack("Nav").commit();
    }

    public void clearBackstack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void setToolBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}

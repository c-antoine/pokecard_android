package com.burelliercervo.androidpokeapi.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.view.FragmentListCard;
import com.burelliercervo.androidpokeapi.view.FragmentProfil;

/**
 * Created by iem on 02/03/2018.
 */

public class MainActivity extends AppCompatActivity {

    private FragmentListCard fragmentListCard;
    private FragmentProfil fragmentProfil;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_accueil:
                    showFragment(new FragmentProfil());
                    return true;
                case R.id.navigation_pokelist0:
                    showFragment(new FragmentListCard());
                    return true;
                case R.id.navigation_pokelist1:
                    showFragment(new FragmentListCard());
                    return true;
                case R.id.navigation_pokelist2:
                    showFragment(new FragmentListCard());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.fragment_container);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        showFragment(new FragmentProfil());

    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

}

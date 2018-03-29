package com.burelliercervo.androidpokeapi.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.model.User;
import com.burelliercervo.androidpokeapi.view.FragmentListCard;
import com.burelliercervo.androidpokeapi.view.FragmentProfil;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

/**
 * Created by iem on 02/03/2018.
 */

public class MainActivity extends BaseActivity {

    private FragmentListCard fragmentListCard;
    private FragmentProfil fragmentProfil;
    private User user;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_accueil:
                    changeFragment(fragmentListCard);
                    return true;
                case R.id.navigation_pokelist0:
                    changeFragment(fragmentProfil);
                    return true;
                case R.id.navigation_pokelist1:
                    changeFragment(fragmentProfil);
                    return true;
                case R.id.navigation_pokelist2:
                    changeFragment(fragmentProfil);
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

        //Créer les instances des fragments
        iniFragment();

        //Attribuer au fragment les informations de l'utilisateur
        getDataFromConnexion();

//        changeFragment(fragmentListCard);
    }

    public void iniFragment(){
        this.fragmentListCard = new FragmentListCard();
        this.fragmentProfil = new FragmentProfil();
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    public void getDataFromConnexion(){
        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("userProfile");
//        Log.w("Jsondata", jsondata);
        JSONObject response, profile_pic_data, profile_pic_url;
        try {
            response = new JSONObject(jsondata);
            Bundle bundle = new Bundle();
            bundle.putString("userID", "10");
//            bundle.putString("userID", response.get("id").toString());
            this.fragmentListCard.setArguments(bundle);

            // set Fragmentclass Arguments

        } catch(Exception e){
            e.printStackTrace();
        }
    }

}

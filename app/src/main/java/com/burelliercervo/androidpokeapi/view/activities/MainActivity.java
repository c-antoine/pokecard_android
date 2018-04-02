package com.burelliercervo.androidpokeapi.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.manager.IFragmentManager;
import com.burelliercervo.androidpokeapi.model.User;
import com.burelliercervo.androidpokeapi.view.fragments.FragmentDetails;
import com.burelliercervo.androidpokeapi.view.fragments.FragmentListCard;
import com.burelliercervo.androidpokeapi.view.fragments.FragmentProfil;

import org.json.JSONObject;

/**
 * Created by iem on 02/03/2018.
 */

public class MainActivity extends BaseActivity implements IFragmentManager {

    private FragmentListCard fragmentListCard;
    private FragmentDetails fragmentDetails;
    private FragmentProfil fragmentProfil;
    private User actualUser;

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
        iniFragment();
        actualUser = User.getInstance();

        setContentView(R.layout.fragment_container);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Créer les instances des fragments

        //Attribuer au fragment les informations de l'utilisateur
        getDataFromConnexion();

        changeFragment(fragmentListCard);
    }

    public void iniFragment(){
        this.fragmentListCard = FragmentListCard.getInstanceFragment();
        this.fragmentProfil = new FragmentProfil();
        this.fragmentDetails = FragmentDetails.getInstanceFragment();
    }

    @Override
    public void onDataPass(String data) {
        Bundle bundle = new Bundle();
        bundle.putString("pokemonID", data);
        fragmentDetails = new FragmentDetails();
        fragmentDetails.setArguments(bundle);
        changeFragment(fragmentDetails);
    }

    public void getDataFromConnexion(){
        Intent intent = getIntent();
        String jsondata = intent.getStringExtra("data");
//        jsondata.get(0);
//        actualUser.setId(Integer.parseInt(jsondata));
//        Log.w("Jsondata", jsondata);
        JSONObject response, profile_pic_data, profile_pic_url;
        try {
            response = new JSONObject(jsondata);
            Bundle bundle = new Bundle();
            bundle.putString("userID", String.valueOf(actualUser.getId()));
//            bundle.putString("userID", response.get("id").toString());
            this.fragmentListCard.setArguments(bundle);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}

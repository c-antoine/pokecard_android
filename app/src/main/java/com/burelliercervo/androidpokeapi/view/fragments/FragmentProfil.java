package com.burelliercervo.androidpokeapi.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.model.User;

/**
 * Created by iem on 02/03/2018.
 */

public class FragmentProfil extends BaseFragment {
    public static FragmentProfil instanceFragment;
    private TextView tvEmail;
    private TextView tvNomPrenom;
    private ImageView imgPicture;
    private User actualUser;
    private View v;

    public static FragmentProfil getInstanceFragment() {
        if (instanceFragment == null) {
            instanceFragment = new FragmentProfil();
        }
        return instanceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_user_profile, container, false);
        iniComponent(v);

        actualUser = User.getInstance();
        tvEmail.setText(actualUser.getEmail());
        tvNomPrenom.setText(actualUser.getName());
        Glide.with(context)
                .load(actualUser.getPicture())
                .into(imgPicture);
        return v;
    }

    public void iniComponent(View v){
        tvNomPrenom = (TextView) v.findViewById(R.id.pNomPrenom);
        imgPicture = (ImageView) v.findViewById(R.id.pPicture);
        tvEmail = (TextView) v.findViewById(R.id.pEmail);
    }
}

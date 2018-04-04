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
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

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
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        iniComponent();
        User.getInstance().getPicture();
//        Picasso.with(context).load(User.getInstance().getPicture()).into(imgPicture);
        tvEmail.setText(User.getInstance().getEmail());
        tvNomPrenom.setText(User.getInstance().getName());
        return v;
    }

    public void iniComponent(){
        tvNomPrenom = (TextView) v.findViewById(R.id.tvNomPrenom);
        imgPicture = (ImageView) v.findViewById(R.id.imgPicture);
        tvEmail = (TextView) v.findViewById(R.id.tvEmail);
    }
}

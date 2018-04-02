package com.burelliercervo.androidpokeapi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.burelliercervo.androidpokeapi.R;

public class PokedexHolder extends RecyclerView.ViewHolder {

    public ImageView sprite;

    public PokedexHolder(View view) {
        super(view);
        sprite = (ImageView) view.findViewById(R.id.pokemonSprite);
    }

}

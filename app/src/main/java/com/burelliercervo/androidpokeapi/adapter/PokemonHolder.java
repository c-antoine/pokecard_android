package com.burelliercervo.androidpokeapi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.burelliercervo.androidpokeapi.R;

public class PokemonHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView xp;
    public TextView type;
    public ImageView sprite;

    public PokemonHolder(View view) {
        super(view);
        name = (TextView) view.findViewById(R.id.pokeName);
        xp = (TextView) view.findViewById(R.id.pokeXp);
        type = (TextView) view.findViewById(R.id.pokeType);
        sprite = (ImageView) view.findViewById(R.id.pokemonVSprite);
    }

}

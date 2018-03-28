package com.burelliercervo.androidpokeapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.model.Pokemon;

import java.util.ArrayList;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private ArrayList<Pokemon> pokemons;
    private Context context;

    public PokemonListAdapter(ArrayList<Pokemon> pokemonArrayList, Context context){
        this.pokemons = pokemonArrayList;
        this.context = context;
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.adapter_item_pokemon;
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(layout, parent, false);
        return new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        holder.name.setText(pokemons.get(position).getName());
        holder.xp.setText(pokemons.get(position).getXp());
        holder.type.setText(pokemons.get(position).getType());
        Glide.with(context)
                .load(pokemons.get(position).getSprite())
                .into(holder.sprite);
    }

    @Override
    public int getItemCount() {
        //return 5;
        return pokemons.size();
    }
}

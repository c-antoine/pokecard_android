package com.burelliercervo.androidpokeapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.model.Pokemon;

import java.util.List;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexHolder> {

    private List<Pokemon> pokemons;
    private Context context;

    public PokedexAdapter(List<Pokemon> pokemonList, Context context){
        this.pokemons = pokemonList;
        this.context = context;
    }

    @Override
    public PokedexHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.adapter_pokedex;
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(layout, parent, false);
        return new PokedexHolder(v);
    }

    @Override
    public void onBindViewHolder(PokedexHolder holder, int position) {
        Glide.with(context)
                .load(pokemons.get(position).getSpriteWithID(pokemons.get(position).getId()))
                .into(holder.sprite);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void updateData(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}

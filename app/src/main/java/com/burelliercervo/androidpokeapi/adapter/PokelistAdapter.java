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

public class PokelistAdapter extends RecyclerView.Adapter<PokelistHolder> {

    private List<Pokemon> pokemons;
    private Context context;

    public PokelistAdapter(List<Pokemon> pokemonList, Context context){
        this.pokemons = pokemonList;
        this.context = context;
    }

    @Override
    public PokelistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.adapter_item_pokemon;
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(layout, parent, false);
        return new PokelistHolder(v);
    }

    @Override
    public void onBindViewHolder(PokelistHolder holder, int position) {
        holder.name.setText(pokemons.get(position).getName());
        holder.xp.setText(pokemons.get(position).getXp());
        holder.type.setText(pokemons.get(position).getType());
        Glide.with(context)
                .load(pokemons.get(position).getSprite())
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

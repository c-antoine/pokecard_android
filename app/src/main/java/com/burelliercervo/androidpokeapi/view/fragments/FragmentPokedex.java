package com.burelliercervo.androidpokeapi.view.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.adapter.ItemClickSupport;
import com.burelliercervo.androidpokeapi.adapter.PokedexAdapter;
import com.burelliercervo.androidpokeapi.adapter.PokelistAdapter;
import com.burelliercervo.androidpokeapi.manager.PokemonManager;
import com.burelliercervo.androidpokeapi.model.Pokemon;
import com.burelliercervo.androidpokeapi.model.User;

import java.util.ArrayList;
import java.util.List;

public class FragmentPokedex extends BaseFragment {
    public static FragmentPokedex instanceFragment;
    private RecyclerView pokedexRecycler;
    private PokedexAdapter pokedexAdapter;
    private View v;
    private List<Pokemon> pokemons = new ArrayList<>();

    public static FragmentPokedex getInstanceFragment() {
        if (instanceFragment == null) {
            instanceFragment = new FragmentPokedex();
        }
        return instanceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final PokemonManager p = PokemonManager.getInstance();
//        async = getArguments().getBoolean("async", false);
        View v = inflater.inflate(R.layout.fragment_recycler_pokedex, container, false);

        pokedexRecycler = (RecyclerView) v.findViewById(R.id.recycler_viewPokedex);
        pokedexRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
        pokedexAdapter = new PokedexAdapter(pokemons, context);

//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
//        pokedexRecyclerView.setLayoutManager(layoutManager);

//        ItemClickSupport.addTo(pokedexRecycler).setOnItemClickListener(
//                new ItemClickSupport.OnItemClickListener() {
//                    @Override
//                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                        Pokemon selectedPokemon = pokemons.get(position);
//                        passData(String.valueOf(selectedPokemon.getId()));
//                    }
//                }
//        );

        pokedexRecycler.setAdapter(pokedexAdapter);

//        pokedexRecycler.setClickable(true);

        v = afficherPokedex(v);

        return v;
    }

    public View afficherPokedex(View v) {
        new Thread(new Runnable() {
                @Override
                public void run() {
                    pokemons = PokemonManager.getInstance().getPokedex();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pokedexAdapter.updateData(pokemons);
                            pokedexAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }).start();
            return v;

    }



}

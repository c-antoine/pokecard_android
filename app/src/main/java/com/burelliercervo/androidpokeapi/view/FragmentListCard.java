package com.burelliercervo.androidpokeapi.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.adapter.PokemonListAdapter;
import com.burelliercervo.androidpokeapi.manager.PokemonManager;
import com.burelliercervo.androidpokeapi.model.Pokemon;
import com.burelliercervo.androidpokeapi.manager.IPokemonWs;
import com.burelliercervo.androidpokeapi.model.PokemonWs;

import java.util.ArrayList;

import static okhttp3.internal.Internal.instance;

/**
 * Created by iem on 02/03/2018.
 */

public class FragmentListCard extends BaseFragment {
    RecyclerView recyclerView;
    PokemonListAdapter adapter;
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    public static FragmentListCard instanceFragment;
    private View v;

    public static FragmentListCard getInstanceFragment() {
        if (instanceFragment == null) {
            instanceFragment = new FragmentListCard();
        }
        return instanceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final PokemonManager p = PokemonManager.getInstance();
//        async = getArguments().getBoolean("async", false);
        v = inflater.inflate(R.layout.fragment_recycler_listpokemons, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokemonListAdapter(pokemons, context);
        recyclerView.setAdapter(adapter);

        afficherPokemons();

        return v;
    }

    private void afficherPokemons() {

        PokemonManager.getInstance().getPokemonsForUser(new IPokemonWs() {
            @Override
            public void onSuccess(PokemonWs pokemonsWs) {
                FragmentListCard.getInstanceFragment().pokemons = pokemonsWs.getPokemons();

                getInstanceFragment().context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onError() {
                //Yolo
            }
        });
    }

}

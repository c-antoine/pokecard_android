package com.burelliercervo.androidpokeapi.view.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.adapter.PokedexAdapter;
import com.burelliercervo.androidpokeapi.manager.SNetworkManager;
import com.burelliercervo.androidpokeapi.manager.SPokemonManager;
import com.burelliercervo.androidpokeapi.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class FragmentPokedex extends BaseFragment {
    public static FragmentPokedex instanceFragment;
    private RecyclerView pokedexRecycler;
    private PokedexAdapter pokedexAdapter;
    private View v;
    private List<Pokemon> pokemons = new ArrayList<>();
    private SNetworkManager SNetworkManager;

//    public static FragmentPokedex getInstanceFragment() {
//        if (instanceFragment == null) {
//            instanceFragment = new FragmentPokedex();
//        }
//        return instanceFragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final SPokemonManager p = SPokemonManager.getInstance();
        View v = inflater.inflate(R.layout.fragment_recycler_pokedex, container, false);

        pokedexRecycler = (RecyclerView) v.findViewById(R.id.recycler_viewPokedex);
        pokedexRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
        pokedexAdapter = new PokedexAdapter(pokemons, context);
        pokedexRecycler.setAdapter(pokedexAdapter);

        SNetworkManager = SNetworkManager.getInstance();
        if(SNetworkManager.isOnline()){
            v = afficherPokedex(v);
        }
        else{
            Toast.makeText(this.getActivity(), "Vous n'êtes pas connecté à internet", Toast.LENGTH_LONG).show();
        }

        return v;
    }

    public View afficherPokedex(View v) {
        new Thread(new Runnable() {
                @Override
                public void run() {
                    pokemons = SPokemonManager.getInstance().getPokedex();
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

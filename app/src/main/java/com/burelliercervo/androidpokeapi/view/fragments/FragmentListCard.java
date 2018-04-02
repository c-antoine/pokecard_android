package com.burelliercervo.androidpokeapi.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.adapter.ItemClickSupport;
import com.burelliercervo.androidpokeapi.adapter.PokelistAdapter;
import com.burelliercervo.androidpokeapi.manager.IFragmentManager;
import com.burelliercervo.androidpokeapi.manager.PokemonManager;
import com.burelliercervo.androidpokeapi.model.Pokemon;
import com.burelliercervo.androidpokeapi.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iem on 02/03/2018.
 */

public class FragmentListCard extends BaseFragment {
    RecyclerView recyclerView;
    PokelistAdapter adapter;
    List<Pokemon> pokemons = new ArrayList<>();
    public static FragmentListCard instanceFragment;
    private View v;
    private IFragmentManager dataPasser;
    private User actualUser;

    public static FragmentListCard getInstanceFragment() {
        if (instanceFragment == null) {
            instanceFragment = new FragmentListCard();
        }
        return instanceFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (IFragmentManager) context;
    }

    public void passData(String data) {
        dataPasser.onDataPass(data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final PokemonManager p = PokemonManager.getInstance();
//        async = getArguments().getBoolean("async", false);
        v = inflater.inflate(R.layout.fragment_recycler_listpokemons, container, false);

        actualUser = User.getInstance();

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_viewPokelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokelistAdapter(pokemons, context);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Pokemon selectedPokemon = pokemons.get(position);
                        passData(String.valueOf(selectedPokemon.getId()));
                    }
                }
        );

        recyclerView.setAdapter(adapter);

        recyclerView.setClickable(true);

        v = afficherPokemons(v, actualUser);

        return v;
    }

    public View afficherPokemons(View v, final User u){
        new Thread(new Runnable() {
            @Override
            public void run() {
                pokemons = PokemonManager.getInstance().getAllPokemon(actualUser.getId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateData(pokemons);
                        adapter.notifyDataSetChanged();

                    }
                });
            }
        }).start();
        return v;
    }
}

package com.burelliercervo.androidpokeapi.view.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
    ProgressDialog dialog;

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
        dialog = new ProgressDialog(context);
//        loadingPop();
        v = inflater.inflate(R.layout.fragment_recycler_listpokemons, container, false);

//        actualUser = User.getInstance();
        actualUser = User.getInstance();
        actualUser.setId(2147483647);
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
        loadPop();
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadPop();
                pokemons = PokemonManager.getInstance().getAllPokemon(actualUser.getId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateData(pokemons);
                        adapter.notifyDataSetChanged();
                        handler.sendEmptyMessage(0);
                    }
                });
            }
        }).start();
        return v;
    }

    public void loadPop(){
        this.dialog.setMessage("loading...");
        this.dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        this.dialog.setMax(100);
        this.dialog.setProgress(0);
        this.dialog.show();
        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalProgressTime) {
                    try {
                        sleep(200);
                        jumpTime += 5;
                        dialog.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }
        };
        t.start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dialog.dismiss();
        }
    };
}

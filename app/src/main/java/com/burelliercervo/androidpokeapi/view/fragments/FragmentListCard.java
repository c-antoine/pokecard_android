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
import android.widget.Toast;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.adapter.ItemClickSupport;
import com.burelliercervo.androidpokeapi.adapter.PokelistAdapter;
import com.burelliercervo.androidpokeapi.manager.IFragmentManager;
import com.burelliercervo.androidpokeapi.manager.SNetworkManager;
import com.burelliercervo.androidpokeapi.manager.SPokemonManager;
import com.burelliercervo.androidpokeapi.model.Pokemon;
import com.burelliercervo.androidpokeapi.model.SUser;

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
    private SPokemonManager SPokemonManager;
    private Boolean alreadyLooped = false;

    private IFragmentManager dataPasser;
    private SUser actualSUser;
    ProgressDialog dialog;
    private SNetworkManager SNetworkManager;

//    public static FragmentListCard getInstanceFragment() {
//        if (instanceFragment == null) {
//            instanceFragment = new FragmentListCard();
//        }
//        return instanceFragment;
//    }

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

        SPokemonManager = SPokemonManager.getInstance();
        dialog = new ProgressDialog(context);
        v = inflater.inflate(R.layout.fragment_recycler_listpokemons, container, false);

        actualSUser = SUser.getInstance();
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

        SNetworkManager = SNetworkManager.getInstance();
        if(SNetworkManager.isOnline()){
            v = afficherPokemons(v, actualSUser);
        }
        else{
            Toast.makeText(this.getActivity(), "Vous n'êtes pas connecté à internet", Toast.LENGTH_LONG).show();
        }
        return v;
    }

    public View afficherPokemons(View v, final SUser u){
        if(!alreadyLooped){
            loadPop();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                pokemons = SPokemonManager.getInstance().getAllPokemon(actualSUser.getId());
                dialog.dismiss();
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
        this.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //this.dialog.setMax(100);
        //this.dialog.setProgress(0);
        this.dialog.show();
        /*final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalProgressTime) {
                    try {
                        sleep(100);
                        jumpTime += 30;
                        dialog.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
//                dialog.dismiss();
            }
        };
        t.start();*/
        alreadyLooped = true;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dialog.dismiss();
        }
    };
}

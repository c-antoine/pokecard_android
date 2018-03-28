package com.burelliercervo.androidpokeapi.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.adapter.MyListAdapter;
import com.burelliercervo.androidpokeapi.manager.PokemonManager;
import com.burelliercervo.androidpokeapi.model.Pokemon;
import com.burelliercervo.androidpokeapi.manager.IPokemonWs;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by iem on 02/03/2018.
 */

public class FragmentListCard extends BaseFragment {
    private ListView mListView;
    private int offset = 0;
    private PokemonManager pokemonManager;

    RecyclerView recyclerView;
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    PokemonListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final PokemonManager p = PokemonManager.getInstance();
//        async = getArguments().getBoolean("async", false);
        View v = inflater.inflate(R.layout.fragment_recycler_listpokemons, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PokemonListAdapter();
        recyclerView.setAdapter(adapter);

        afficherPokemons();

//        initComponent();

//        String strtext = getArguments().getString("userID");
//        this.listPokemonsTask.execute(strtext);

        return v;
    }

//    public void initComponent(){
//        this.listPokemonsTask = new ListPokemonsTask();
//
//    }

    private void afficherPokemons() {
//        context.setToolBarTitle("Pokemon Liste SYNC");

        PokemonManager.getInstance().getPokemonsForUser(new IPokemonWs() {
            @Override
            public void onSuccess(ArrayList<Pokemon> pokemons) {
                FragmentListCard.this.pokemons = pokemons;
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onError() {

            }
        });
    }



//    public void afficherPokemons(List<Pokemon> PokemonsList) {
//
//        myListAdapter = new PokemonListAdapter(PokemonsList, this.getActivity());
//        mListView = (ListView) getActivity().findViewById(R.id.listview);
//        mListView.setAdapter(myListAdapter);
//        myListAdapter.notifyDataSetChanged();
//        Toast.makeText(this.getActivity(),"nombre de Pokemon : "+PokemonsList.size(),Toast.LENGTH_SHORT).show();
//
////        mListView.setClickable(true);
////        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////
////            @Override
////            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
////
////                Object o = mListView.getItemAtPosition(position);
////                Log.d("TAG", "onItemClick: " + o);
////                Intent myIntent = new Intent(ListCard.this, DetailCard.class);
////                ListCard.this.startActivity(myIntent);
////                myIntent.putExtra(EXTRA_MESSAGE, o.toString());
////                startActivityForResult(myIntent, 0);
////            }
////        });
//    }


    private class PokemonViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView xp;
        public TextView type;
        public ImageView sprite;

        public PokemonViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.pokeName);
            xp = (TextView) view.findViewById(R.id.pokeXp);
            type = (TextView) view.findViewById(R.id.pokeType);
            sprite = (ImageView) view.findViewById(R.id.pokemonVSprite);
        }
    }

    private class PokemonListAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

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

}

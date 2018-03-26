package com.burelliercervo.androidpokeapi.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.adapter.MyListAdapter;
import com.burelliercervo.androidpokeapi.model.Pokemon;
import com.burelliercervo.androidpokeapi.service.ListPokemonsTask;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by iem on 02/03/2018.
 */

public class FragmentListCard extends Fragment {
    MyListAdapter myListAdapter;
    private ListView mListView;
    private List<Pokemon> pokemons = new ArrayList<>();
    private int offset = 0;
    private ListPokemonsTask listPokemonsTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initComponent();

        String strtext = getArguments().getString("userID");
        this.listPokemonsTask.execute(strtext);

        return inflater.inflate(R.layout.fragment_list_card, container, false);
    }

    public void initComponent(){
        this.listPokemonsTask = new ListPokemonsTask();
    }

    public void afficherPokemons(List<Pokemon> PokemonsList) {

        myListAdapter = new MyListAdapter(PokemonsList, this.getActivity());
        mListView = (ListView) getActivity().findViewById(R.id.listview);
        mListView.setAdapter(myListAdapter);
        myListAdapter.notifyDataSetChanged();
        Toast.makeText(this.getActivity(),"nombre de Pokemon : "+PokemonsList.size(),Toast.LENGTH_SHORT).show();

//        mListView.setClickable(true);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//
//                Object o = mListView.getItemAtPosition(position);
//                Log.d("TAG", "onItemClick: " + o);
//                Intent myIntent = new Intent(ListCard.this, DetailCard.class);
//                ListCard.this.startActivity(myIntent);
//                myIntent.putExtra(EXTRA_MESSAGE, o.toString());
//                startActivityForResult(myIntent, 0);
//            }
//        });
    }

}

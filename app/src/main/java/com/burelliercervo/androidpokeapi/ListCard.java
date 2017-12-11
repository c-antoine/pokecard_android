package com.burelliercervo.androidpokeapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListCard extends AppCompatActivity {

    public static final String URL = "https://pokeapi.co/api/v2/pokedex/1/";
    MyListAdapter myListAdapter;
    private ListView mListView;
    private List<Pokemon> pokemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_card);
        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));


        myListAdapter = new MyListAdapter(pokemons, this);
        mListView = (ListView) findViewById(R.id.listview);

        mListView.setAdapter(myListAdapter);

        myListAdapter.notifyDataSetChanged();


    }

}
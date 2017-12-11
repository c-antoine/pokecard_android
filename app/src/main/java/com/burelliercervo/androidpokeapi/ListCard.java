package com.burelliercervo.androidpokeapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListCard extends AppCompatActivity {

    //    public static final String URL = "https://pokeapi.co/api/v2/pokedex/1/";
    MyListAdapter myListAdapter;
    private ListView mListView;
    private List<Pokemon> pokemons = new ArrayList<>();

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_card);
//        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
//        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
//        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
//        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
//        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
//        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
//        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
//        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
//        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
//        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
//        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
//        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
//        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
//        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
//        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
//        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
//        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
//        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
//        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
//        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
//
//
//        myListAdapter = new MyListAdapter(pokemons, this);
//        mListView = (ListView) findViewById(R.id.listview);
//
//        mListView.setAdapter(myListAdapter);
//
//        myListAdapter.notifyDataSetChanged();
//
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_card);
         new ListPokemonsTask().execute("1");

    }

    public void afficherPokemons(List<Pokemon> Pokemons) {
        //Pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));

        myListAdapter = new MyListAdapter(pokemons, this);
        mListView = (ListView) findViewById(R.id.listview);
//
        mListView.setAdapter(myListAdapter);
//
        myListAdapter.notifyDataSetChanged();
        Toast.makeText(this,"nombre de Pokemon : "+Pokemons.size(),Toast.LENGTH_SHORT).show();



    }

    class ListPokemonsTask extends AsyncTask<String,Void,List<Pokemon>> {

        @Override
        protected List<Pokemon> doInBackground(String...params) {
            pokeapiService pokeapiService = new RestAdapter.Builder()
                    .setEndpoint(com.burelliercervo.androidpokeapi.pokeapiService.ENDPOINT)
                    .build()
                    .create(pokeapiService.class);

            String user = params[0];
//            List<Pokemon> PokemonList = pokeapiService.listPokemon("123");
            //List<Pokemon> PokemonList = pokeapiService.listPokemon("123");
            List<Pokemon> PokemonList = pokeapiService.listRepos(user);
            //List PokemonList = pokeapiService.listPokemon("123");
            return PokemonList;
        }

        @Override
        protected void onPostExecute(List<Pokemon> Pokemons) {
            super.onPostExecute(Pokemons);
            afficherPokemons(Pokemons);
        }
    }

}





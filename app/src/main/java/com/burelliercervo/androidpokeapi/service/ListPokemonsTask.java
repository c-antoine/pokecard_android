//package com.burelliercervo.androidpokeapi.service;
//
//import android.os.AsyncTask;
//
//import com.burelliercervo.androidpokeapi.manager.PokemonManager;
//import com.burelliercervo.androidpokeapi.manager.RetrofitManager;
//import com.burelliercervo.androidpokeapi.model.Pokemon;
//import com.burelliercervo.androidpokeapi.model.PokemonWs;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Response;
//
///**
// * Created by iem on 09/03/2018.
// */
//
//public class ListPokemonsTask extends AsyncTask<String,Void,List<Pokemon>> {
//
//    private RetrofitManager retrofitManager;
//    private PokemonManager pokemonManager;
//    private List<Pokemon> pokemons = new ArrayList<>();
//
//    public ListPokemonsTask(){
//        this.pokemonManager = this.pokemonManager.getInstance();
//        this.retrofitManager = new RetrofitManager();
//    }
//
//    public List<Pokemon> getPokemons(){
//        return this.pokemons;
//    }
//
//    @Override
//    protected List<Pokemon> doInBackground(String...params) {
//
//        List<Pokemon> pokemons = new ArrayList<>();
//
//        IPokeapiService service = this.retrofitManager.initRetrofit();
//
//        Call<List<PokemonWs>> call =  service.listPokemon(10);
//        try {
//            Response<List<Pokemon>> response =  call.execute();
//            if(response.isSuccessful()) {
//                pokemons = response.body();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return pokemons;
//    }
//
//    @Override
//    protected void onPostExecute(List<Pokemon> Pokemons) {
//        super.onPostExecute(Pokemons);
//        this.pokemonManager.setPokemons(Pokemons);
//    }
//
//}

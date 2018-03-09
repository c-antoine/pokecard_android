package com.burelliercervo.androidpokeapi.service;

import android.os.AsyncTask;

import com.burelliercervo.androidpokeapi.manager.RetrofitManager;
import com.burelliercervo.androidpokeapi.model.Pokemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by iem on 09/03/2018.
 */

public class ListPokemonsTask extends AsyncTask<String,Void,List<Pokemon>> {

    private RetrofitManager retrofitManager;

    public ListPokemonsTask(){
        this.retrofitManager = new RetrofitManager();
    }

    @Override
    protected List<Pokemon> doInBackground(String...params) {

        List<Pokemon> pokemons = new ArrayList<>();

        PokeapiService service = this.retrofitManager.initRetrofit();

        Call<List<Pokemon>> call =  service.listPokemon(10);
        try {
            Response<List<Pokemon>> response =  call.execute();
            if(response.isSuccessful()) {
                pokemons = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pokemons;
    }

    @Override
    protected void onPostExecute(List<Pokemon> Pokemons) {
        super.onPostExecute(Pokemons);
//        afficherPokemons(Pokemons);
    }
}

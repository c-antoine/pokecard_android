package com.burelliercervo.androidpokeapi.manager;

import com.burelliercervo.androidpokeapi.model.Pokemon;
import com.burelliercervo.androidpokeapi.model.PokemonWs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PokemonManager {
    private static PokemonManager instance;
    private List<Pokemon> pokemons = new ArrayList<>();

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    private PokemonManager() {
        //empty constructor
    }

    public static PokemonManager getInstance() {
        if (instance == null) {
            instance = new PokemonManager();
        }
        return instance;
    }

    public void getPokemonsForUser(final IPokemonWs listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<Pokemon> call = RetrofitManager.getRetrofitContent().listPokemon(10);
                try {
                    Response<Pokemon> response = call.execute();
                    if (response.isSuccessful()) {
                        listener.onSuccess(response.body().getPokemonsArrayList());
                    } else {
                        listener.onError();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onError();
                }
            }
        }).start();
    }
}

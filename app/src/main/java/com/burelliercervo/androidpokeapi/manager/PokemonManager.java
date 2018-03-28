package com.burelliercervo.androidpokeapi.manager;

import android.util.Log;
import android.widget.Toast;

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
    private Thread runtimePokemonForUser;

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
        runtimePokemonForUser = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<PokemonWs> call = RetrofitManager.getRetrofitContent().listPokemon(10);
                try {
                    Response<PokemonWs> response = call.execute();
                    if (response.isSuccessful()) {
                        instance.setPokemons(pokemons);
                        listener.onSuccess(response.body());
                    } else {
                        listener.onError();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onError();
                }
            }
        });
        runtimePokemonForUser.start();
    }
}

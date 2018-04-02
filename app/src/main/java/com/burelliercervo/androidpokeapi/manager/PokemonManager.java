package com.burelliercervo.androidpokeapi.manager;

import android.util.Log;
import android.widget.Toast;

import com.burelliercervo.androidpokeapi.model.Pokemon;
import com.burelliercervo.androidpokeapi.model.PokemonWs;
import com.burelliercervo.androidpokeapi.service.IPokeapiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PokemonManager {
    private static PokemonManager instance;
    private static IPokeapiService service;
    private List<Pokemon> pokemons = new ArrayList<>();
    private Pokemon pokemon;
    private Thread runtimePokemonForUser;

    public List<Pokemon> getPokemons() {
        return pokemons;
    }
    public Pokemon getPokemon(){ return pokemon; }

    public void setPokemon(Pokemon pokemon){ this.pokemon = pokemon; }
    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    private PokemonManager() {
        //empty constructor
    }

    public static PokemonManager getInstance() {
        if (instance == null) {
            instance = new PokemonManager();
            service = RetrofitManager.getRetrofitContent();
        }
        return instance;
    }

//    public void getPokemonsForUser(final IPokemonWs listener){
//        runtimePokemonForUser = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Call<PokemonWs> call = RetrofitManager.getRetrofitContent().listPokemon();
//                try {
//                    Response<PokemonWs> response = call.execute();
//                    if (response.isSuccessful()) {
////                        PokemonWs pokemonWs = response.body();
//                        listener.onSuccess(response.body());
//                    } else {
//                        listener.onError();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    listener.onError();
//                }
//            }
//        });
//        runtimePokemonForUser.start();
//    }
//

//    public void getPokemonDetails(final int pokemonID, final IPokemon listener){
//        runtimePokemonForUser = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Call<Pokemon> call = RetrofitManager.getRetrofitContent().getPokemon(pokemonID);
//                try {
//                    Response<Pokemon> response = call.execute();
//                    if (response.isSuccessful()) {
////                        Pokemon pokemon = response.body();
//                        listener.onSuccess(response.body());
//                    } else {
//                        listener.onError();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    listener.onError();
//                }
//            }
//        });
//        runtimePokemonForUser.start();
//    }

    public Pokemon getPokemonDetails(int pokemonID) {
        Pokemon pokemons = new Pokemon();
        Call<Pokemon> call = service.getPokemon(pokemonID);
        try {
            Response<Pokemon> response = call.execute();
            if (response.isSuccessful()) {
                pokemon = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokemon;
    }

    public List<Pokemon> getAllPokemon(int id) {
        List<Pokemon> pokemons = new ArrayList<>();
        Call<List<Pokemon>> call = service.listPokemon(id);
        try {
            Response<List<Pokemon>> response = call.execute();
            if (response.isSuccessful()) {
                pokemons = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokemons;
    }
}

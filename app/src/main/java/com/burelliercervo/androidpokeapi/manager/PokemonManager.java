package com.burelliercervo.androidpokeapi.manager;

import com.burelliercervo.androidpokeapi.model.Pokemon;
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

    public List<Pokemon> getPokedex(){
        List<Pokemon> pokemons = new ArrayList<>();
        Call<List<Pokemon>> call = service.getPokedex();
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

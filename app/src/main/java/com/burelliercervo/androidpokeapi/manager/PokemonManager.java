package com.burelliercervo.androidpokeapi.manager;

import com.burelliercervo.androidpokeapi.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

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
}

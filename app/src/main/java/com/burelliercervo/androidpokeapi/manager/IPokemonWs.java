package com.burelliercervo.androidpokeapi.manager;

import com.burelliercervo.androidpokeapi.model.Pokemon;

import java.util.ArrayList;

public interface IPokemonWs {
    public void onSuccess(ArrayList<Pokemon> pokemons);
    public void onError();
}

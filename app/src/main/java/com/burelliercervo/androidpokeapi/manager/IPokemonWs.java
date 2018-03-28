package com.burelliercervo.androidpokeapi.manager;

import com.burelliercervo.androidpokeapi.model.PokemonWs;

public interface IPokemonWs {
    public void onSuccess(PokemonWs pokemons);
    public void onError();
}

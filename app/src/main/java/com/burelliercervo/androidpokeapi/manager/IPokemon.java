package com.burelliercervo.androidpokeapi.manager;

import com.burelliercervo.androidpokeapi.model.Pokemon;

public interface IPokemon {
    public void onSuccess(Pokemon pokemon);
    public void onError();
}

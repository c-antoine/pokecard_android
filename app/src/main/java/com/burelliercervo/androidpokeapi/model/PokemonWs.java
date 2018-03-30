package com.burelliercervo.androidpokeapi.model;

import java.util.ArrayList;

public class PokemonWs {
    String previous = "";
    String next = "";
    ArrayList<Pokemon> pokemons = new ArrayList<>();

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}

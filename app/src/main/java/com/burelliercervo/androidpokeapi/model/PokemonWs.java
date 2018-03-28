package com.burelliercervo.androidpokeapi.model;

import java.util.ArrayList;

public class PokemonWs {
    private String previous = "";
    private String next = "";
    private ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();

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

    public ArrayList<Pokemon> getPokemonArrayList() {
        return pokemonArrayList;
    }

    public void setPokemonArrayList(ArrayList<Pokemon> pokemonArrayList) {
        this.pokemonArrayList = pokemonArrayList;
    }

    public PokemonWs(){
        this.pokemonArrayList = new ArrayList<Pokemon>();
    }
}

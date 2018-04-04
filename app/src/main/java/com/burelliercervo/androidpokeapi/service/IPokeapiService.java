package com.burelliercervo.androidpokeapi.service;



import com.burelliercervo.androidpokeapi.model.Pokemon;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iem on 11/12/2017.
 */

public interface IPokeapiService {



//    @GET("lp_iem/result.json")
//    Call<PokemonWs> listPokemon();

    @GET("?action=cardlist&")
    Call<List<Pokemon>> listPokemon(@Query("user")int user);
    //@Query("user")int user

    @GET("?action=details&")
    Call<Pokemon> getPokemon(@Query("card")int id_card);

    @GET("?action=pokedex")
    Call<List<Pokemon>> getPokedex();

}
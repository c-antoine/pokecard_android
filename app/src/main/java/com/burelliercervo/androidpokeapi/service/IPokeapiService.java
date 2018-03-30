package com.burelliercervo.androidpokeapi.service;



import com.burelliercervo.androidpokeapi.model.Pokemon;
import com.burelliercervo.androidpokeapi.model.PokemonWs;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by iem on 11/12/2017.
 */

public interface IPokeapiService {



//    @GET("lp_iem/result.json")
//    Call<PokemonWs> listPokemon();

    @GET("?action=cardlist&user=10")
    Call<PokemonWs> listPokemon();
    //@Query("user")int user

    @GET("?action=details&")
    Call<Pokemon> Pokemon(@Query("card")int id_card);



}
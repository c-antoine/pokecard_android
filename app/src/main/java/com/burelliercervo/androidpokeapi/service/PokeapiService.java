package com.burelliercervo.androidpokeapi.service;



import com.burelliercervo.androidpokeapi.model.Pokemon;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iem on 11/12/2017.
 */

public interface PokeapiService {

    @GET("pokecardAPI")
    Call<List<Pokemon>> listPokemon(@Query("action") String action, @Query("user")int user);

}
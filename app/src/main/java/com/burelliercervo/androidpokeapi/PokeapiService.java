package com.burelliercervo.androidpokeapi;



import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.facebook.HttpMethod.GET;

/**
 * Created by iem on 11/12/2017.
 */

public interface PokeapiService {


    @GET("pokemon")
    Call<List<Pokemon>> listPokemon(@Query("limit") int limit, @Query("offset")int offset);

}
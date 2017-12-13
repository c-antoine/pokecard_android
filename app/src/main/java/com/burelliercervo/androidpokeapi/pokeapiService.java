package com.burelliercervo.androidpokeapi;



import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit2.Call;


import static com.facebook.HttpMethod.GET;

/**
 * Created by iem on 11/12/2017.
 */

public interface pokeapiService {

    public static final String ENDPOINT = "https://pokeapi.co/api/v2/pokemon";




    @GET("/1")
    List<Pokemon> listPokemon(@Path("weight") String user);

//    @GET("/search/repositories")
//    List<Repo> searchRepos(@Query("q") String query);
}
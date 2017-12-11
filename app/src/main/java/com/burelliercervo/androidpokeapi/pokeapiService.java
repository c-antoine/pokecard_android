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

    public static final String ENDPOINT = "https://api.github.com";


//    @GET("/user")
//    List<Pokemon> listPokemon(@Query("id") String query);

    @GET("/users/{user}/repos")
    List<Pokemon> listRepos(@Path("user") String user);
//    @GET("search/{id}")
//    Call<List<Pokemon>> listPokemon(@Path("id")String id);


//    @GET("/users/{id}")
//    List<Pokemon> listPokemon(@Path("id") String name);

//    @GET("/search/repositories")
//    List<Repo> searchRepos(@Query("q") String query);
}
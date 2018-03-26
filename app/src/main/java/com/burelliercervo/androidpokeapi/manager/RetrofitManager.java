package com.burelliercervo.androidpokeapi.manager;

import com.burelliercervo.androidpokeapi.service.PokeapiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iem on 09/03/2018.
 */

public class RetrofitManager {

    private PokeapiService retrofitContent;
    private static final String API_URL = "http://antoinecervo.com/pokecardAPI/";

    public PokeapiService initRetrofit() {
        Retrofit.Builder mBuilder =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        //if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okBuilder.addInterceptor(logging);
        //}
        okBuilder.readTimeout(1, TimeUnit.MINUTES);
//          pokemons = PokeapiService.listPokemon(20, offset);
        OkHttpClient httpClient = okBuilder.build();
        Retrofit retrofit = mBuilder.client(httpClient).build();
        return retrofit.create(PokeapiService.class);
    }

    public PokeapiService getRetrofitContent() {
        return retrofitContent;
    }

    public void setRetrofitContent(PokeapiService retrofitContent) {
        this.retrofitContent = retrofitContent;
    }

}

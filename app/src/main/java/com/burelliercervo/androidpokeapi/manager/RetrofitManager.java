package com.burelliercervo.androidpokeapi.manager;

import android.app.Application;

import com.burelliercervo.androidpokeapi.service.IPokeapiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iem on 09/03/2018.
 */

public class RetrofitManager extends Application{

    public static IPokeapiService retrofitContent;
    private static final String API_URL = "http://antoinecervo.com/pokecardAPI/";

    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
    }


    public IPokeapiService initRetrofit() {
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
//          pokemons = IPokeapiService.listPokemon(20, offset);
        OkHttpClient httpClient = okBuilder.build();
        Retrofit retrofit = mBuilder.client(httpClient).build();
        return retrofit.create(IPokeapiService.class);
    }

    public static IPokeapiService getRetrofitContent() {
        return retrofitContent;
    }

}

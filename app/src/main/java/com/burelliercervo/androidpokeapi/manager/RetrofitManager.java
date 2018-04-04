package com.burelliercervo.androidpokeapi.manager;

import android.app.Application;

import com.burelliercervo.androidpokeapi.service.IPokeapiService;
import com.google.gson.GsonBuilder;

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
    private static final String API_ONLINE_URL = "http://antoinecervo.com/pokecardAPI/";
    private static final String API_LOCAL_URL = "http://192.168.42.125/pokecardAPI/";
    private static final String API_URL_TEST = "http://lionel.banand.free.fr/";


    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
    }

    private void initRetrofit() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        okBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okBuilder.readTimeout(30, TimeUnit.SECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        // log for debug
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okBuilder.addInterceptor(logging);

        OkHttpClient httpClient = okBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ONLINE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .serializeNulls()
                        .create()))
                .client(httpClient)
                .build();


        retrofitContent = retrofit.create(IPokeapiService.class);
    }

    public static IPokeapiService getRetrofitContent() {
        return retrofitContent;
    }

}

package com.burelliercervo.androidpokeapi.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.model.Pokemon;
import com.burelliercervo.androidpokeapi.service.PokeapiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class DetailCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_card);


        final Intent intent = getIntent();

        String message = intent.getStringExtra(EXTRA_MESSAGE);
        int id = Integer.parseInt(message.toString());
        getData(id);

    }



    private void getData(int id) {
        Retrofit mBuilderofit = new Retrofit.Builder()
                .baseUrl("http://antoinecervo.com/pokecardAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokeapiService service = mBuilderofit.create(PokeapiService.class);

        Call<Pokemon> call =  service.Pokemon(id);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    Pokemon pokemon = response.body();

                    TextView tvName = (TextView)findViewById(R.id.tvPokemonName);
                    tvName.setText(pokemon.getName());
                    TextView tvLevel = (TextView)findViewById(R.id.tvLevel);
                    tvLevel.setText("Level : " + pokemon.getXp());
                    ImageView imageSprite = (ImageView)findViewById(R.id.imageViewSprite);


                } else {
                    //Log.e(TAG, "onResponse" + response.body());
                }

            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                //Log.e(TAG, "onFailure"+ t.getMessage());
            }
        });

    }






}

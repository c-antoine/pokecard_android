package com.burelliercervo.androidpokeapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

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

public class ListCard extends AppCompatActivity {

    MyListAdapter myListAdapter;
    private ListView mListView;
    private List<Pokemon> pokemons = new ArrayList<>();
    private int offset = 0;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_card);
//        pokemons.add(new Pokemon("Dracaufeu", "24", "Feu"));
//        pokemons.add(new Pokemon("Bulbizar", "38", "Eau"));
//
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_card);
         new ListPokemonsTask().execute("1");

    }

    public void afficherPokemons(List<Pokemon> Pokemons) {

        myListAdapter = new MyListAdapter(pokemons, this);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(myListAdapter);
        myListAdapter.notifyDataSetChanged();
        Toast.makeText(this,"nombre de Pokemon : "+Pokemons.size(),Toast.LENGTH_SHORT).show();
    }

    class ListPokemonsTask extends AsyncTask<String,Void,List<Pokemon>> {
        @Override
        protected List<Pokemon> doInBackground(String...params) {
//            PokeapiService PokeapiService = new RestAdapter.Builder()
//                    .setEndpoint(com.burelliercervo.androidpokeapi.PokeapiService.ENDPOINT)
//                    .build()
//                    .create(PokeapiService.class);


            //String user = params[0];
            offset = 0;

            List<Pokemon> pokemons = new ArrayList<>();

            PokeapiService service = initRetrofit();

            Call<List<Pokemon>> call =  service.listPokemon(20, offset);
            try {
                Response<List<Pokemon>> response =  call.execute();
                if(response.isSuccessful()) {
                    pokemons = response.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return pokemons;
        }

        @Override
        protected void onPostExecute(List<Pokemon> Pokemons) {
            super.onPostExecute(Pokemons);
            afficherPokemons(Pokemons);
        }
    }


    private PokeapiService initRetrofit() {
        Retrofit.Builder mBuilder =
                new Retrofit.Builder()
                        .baseUrl("https://pokeapi.co/api/v2/")
                        //.baseUrl("http://localhost:8888/")
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

}





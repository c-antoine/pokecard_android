package com.burelliercervo.androidpokeapi.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.burelliercervo.androidpokeapi.adapter.MyListAdapter;
import com.burelliercervo.androidpokeapi.service.PokeapiService;
import com.burelliercervo.androidpokeapi.model.Pokemon;
import com.burelliercervo.androidpokeapi.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

//implements MyListAdapter.OnItemClickListener

public class ListCard extends AppCompatActivity {

    MyListAdapter myListAdapter;
    private ListView mListView;
    private List<Pokemon> pokemons = new ArrayList<>();
    private int offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_card);
         new ListPokemonsTask().execute("1");


    }


    public void afficherPokemons(final List<Pokemon> PokemonsList) {

        myListAdapter = new MyListAdapter(PokemonsList, this);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(myListAdapter);
        myListAdapter.notifyDataSetChanged();

        Toast.makeText(this,"nombre de Pokemon : "+PokemonsList.size(),Toast.LENGTH_SHORT).show();

        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Pokemon o = (Pokemon) mListView.getItemAtPosition(position);
                Log.d("TAG", "onItemClick: " + o);
                Intent myIntent = new Intent(ListCard.this, DetailCard.class);
                ListCard.this.startActivity(myIntent);
                myIntent.putExtra(EXTRA_MESSAGE,  o.getId());

                startActivityForResult(myIntent, 0);


            }
        });
    }



    class ListPokemonsTask extends AsyncTask<String,Void,List<Pokemon>> {
        @Override
        protected List<Pokemon> doInBackground(String...params) {
            offset = 0;

            List<Pokemon> pokemons = new ArrayList<>();

            PokeapiService service = initRetrofit();

            Call<List<Pokemon>> call =  service.listPokemon(10);
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

        private final ProgressDialog dialog = new ProgressDialog(ListCard.this);

        protected void onPreExecute() {
            this.dialog.setMessage("loading...");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(List<Pokemon> Pokemons) {
            this.dialog.dismiss();
            super.onPostExecute(Pokemons);
            afficherPokemons(Pokemons);
        }
    }


    private PokeapiService initRetrofit() {
        Retrofit.Builder mBuilder =
                new Retrofit.Builder()
                        .baseUrl("http://antoinecervo.com/pokecardAPI/")
                        //.baseUrl("http://localhost:8888/")
                        .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okBuilder.addInterceptor(logging);

        okBuilder.readTimeout(1, TimeUnit.MINUTES);
        OkHttpClient httpClient = okBuilder.build();
        Retrofit retrofit = mBuilder.client(httpClient).build();
        return retrofit.create(PokeapiService.class);
    }


}





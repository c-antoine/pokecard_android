package com.burelliercervo.androidpokeapi.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.model.Pokemon;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class DetailCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_card);

        TextView tvLevel = (TextView) findViewById(R.id.tvLevel);
        //tvLevel =
        final Intent intent = getIntent();

        String message = intent.getStringExtra(EXTRA_MESSAGE);
        Toast.makeText(this,"m : "+message,Toast.LENGTH_SHORT).show();
    }
}

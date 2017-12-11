package com.burelliercervo.androidpokeapi;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.net.URL;

public class Connexion extends AppCompatActivity {
    CallbackManager callbackManager;
/*
    TextView t=(TextView)findViewById(R.id.tvAffichePokemon);

*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserDetails(loginResult);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            boolean wifi = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            Log.d("NetworkState", "L'interface de connexion active est du Wifi : " + wifi);
            boolean cellular = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            Log.d("NetworkState", "L'interface de connexion active est du Wifi : " + cellular);
            TextView connexionfailed = (TextView)findViewById(R.id.connexionfailed);
            connexionfailed.setVisibility(View.INVISIBLE);
        }
        else{
            TextView connexionfailed = (TextView)findViewById(R.id.connexionfailed);
            connexionfailed.setVisibility(View.VISIBLE);
        }

        String url1 = "https://pokeapi.co/api/v2/pokedex/1/";
//        new RetrieveFeedTask().execute(url1);

      /*  Button btnAffichePokemon = (Button) findViewById(R.id.btnAffichePokemon);
        btnAffichePokemon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //t.setText(param[0]);
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    protected void getUserDetails(LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        Intent intent = new Intent(Connexion.this, UserProfile.class);
                        intent.putExtra("userProfile", json_object.toString());
                        startActivity(intent);
                    }

                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
    }



    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    protected void onStop()
    {
        super.onStop();
        //recharger les dernières informations précédemment enregistrées dans un fichier local ou une BDD locale, lorsque l’application est mise en arrière-plan ou arrêtée.
    }
}
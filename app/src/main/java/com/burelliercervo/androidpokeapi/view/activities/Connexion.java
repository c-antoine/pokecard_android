package com.burelliercervo.androidpokeapi.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.model.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class Connexion extends AppCompatActivity {
    CallbackManager callbackManager;
    private User actualUser;
    private LoginButton loginButton;
    private NetworkInfo networkInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        clickLogin();

        actualUser = User.getInstance();
        initLoginElements();

    }

    public void initLoginElements(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        this.loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Vous n'êtes pas connecté à internet", Toast.LENGTH_LONG).show();
                if(networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                    boolean wifi = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
                    Log.d("NetworkState", "L'interface de connexion active est du Wifi : " + wifi);
                    boolean cellular = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
                    Log.d("NetworkState", "L'interface de connexion active est du Wifi : " + cellular);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Vous n'êtes pas connecté à internet", Toast.LENGTH_LONG).show();
                }
            }
        });

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
                        Intent intent = new Intent(Connexion.this, MainActivity.class);
                        //Ajouter le singleton de l'utilisateur
                        try {
                            actualUser = User.getInstance();
                            int userID = json_object.getInt("id");
                            actualUser.setPicture(json_object.getString("data"));
                            actualUser.setName(json_object.getString("name"));
                            actualUser.setEmail(json_object.getString("email"));
                            actualUser.setId(userID);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("data", json_object.toString());
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


    protected void clickLogin(){
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
    }



}
package com.burelliercervo.androidpokeapi.view.activities;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.burelliercervo.androidpokeapi.R;
import com.burelliercervo.androidpokeapi.manager.SNetworkManager;
import com.burelliercervo.androidpokeapi.model.SUser;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class Connexion extends AppCompatActivity {
    CallbackManager callbackManager;
    private SUser actualSUser;
    private LoginButton loginButton;
    private NetworkInfo networkInfo;
    private SNetworkManager SNetworkManager;
    private ConnectivityManager connectivityManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getSupportActionBar().setTitle("Pokecard");

        callbackManager = CallbackManager.Factory.create();
        clickLogin();

        actualSUser = SUser.getInstance();
        initLoginElements();

    }

    public void initLoginElements(){

        SNetworkManager = SNetworkManager.getInstance();
        SNetworkManager.setConnectivityManager((ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE));
//        SNetworkManager.setNetworkInfo(networkInfo);

        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        this.loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(SNetworkManager.getNetworkInfo() != null && SNetworkManager.getNetworkInfo().isAvailable() && SNetworkManager.getNetworkInfo().isConnected()) {
                    boolean wifi = SNetworkManager.getNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
                    Log.d("NetworkState", "L'interface de connexion active est du Wifi : " + wifi);
                    boolean cellular = SNetworkManager.getNetworkInfo().getType() == ConnectivityManager.TYPE_MOBILE;
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
                        actualSUser.setUserData(json_object);

                        //Debut de MainActivity
                        startActivity(intent);
                    }

                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
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
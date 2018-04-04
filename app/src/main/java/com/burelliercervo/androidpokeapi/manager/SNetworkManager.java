package com.burelliercervo.androidpokeapi.manager;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SNetworkManager {
    private NetworkInfo networkInfo;
    public static SNetworkManager instance;
    private ConnectivityManager connectivityManager;

    public static SNetworkManager getInstance() {
        if (instance == null) {
            instance = new SNetworkManager();
        }
        return instance;
    }

    public NetworkInfo getNetworkInfo() {
        return networkInfo;
    }

    public void setNetworkInfo(NetworkInfo networkInfo) {
        this.networkInfo = networkInfo;
    }

    public static void setInstance(SNetworkManager instance) {
        SNetworkManager.instance = instance;
    }

    public ConnectivityManager getConnectivityManager() {
        return connectivityManager;
    }

    public Boolean isOnline(){
        this.networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            return true;
        }else{
            return false;
        }
    }

    public void setConnectivityManager(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
        this.networkInfo = connectivityManager.getActiveNetworkInfo();
    }
}

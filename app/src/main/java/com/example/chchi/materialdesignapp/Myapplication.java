package com.example.chchi.materialdesignapp;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by chchi on 12/17/14.
 */
public class Myapplication extends Application{

   static  String mClientTokenURL = "http://changchingchi.com/Demo/BrainTreeTestAndroid.php";
    public static String getClientTokenUrl(Context context) {
        String path = "/client_token";

        return mClientTokenURL + path;
    }

    public  boolean isNetWorkConnected(Context context){
         ConnectivityManager mConMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConMgr.getActiveNetworkInfo();
        if(mNetworkInfo != null && mNetworkInfo.isConnected()){
            return true;
        }else{
            return false;
        }

    }



}

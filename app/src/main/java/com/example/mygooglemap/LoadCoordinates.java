package com.example.mygooglemap;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LoadCoordinates extends AsyncTask <String ,Void ,String>{
    private Activity activity;
    public LoadCoordinates( Activity activity){
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(MyActivity.RESPONSE_BROADCAST);
        Bundle bundle = new Bundle();
        bundle.putString(MyActivity.RESPONSE_VALUE , s);
        intent.putExtras(bundle);
        activity.sendBroadcast(intent);

    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection httpURLConnection;
        StringBuilder stringBuilder = null;
        InputStream inputStream = null;
        try {
           URL url = new URL(strings[0]);
           URLConnection urlConnection = url.openConnection();

               httpURLConnection = (HttpURLConnection)urlConnection;
               httpURLConnection.setRequestMethod("GET");
               httpURLConnection.connect();

           if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
               inputStream = httpURLConnection.getInputStream();
           }else {
               return null ;
           }
            int BUFFER_SIZE = 2000;
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            int charRead;

            stringBuilder = new StringBuilder();
            char[]inputBuffer = new char[BUFFER_SIZE];
            while((charRead = streamReader.read(inputBuffer)) > 0){
                stringBuilder.append(inputBuffer);
                inputBuffer = new char[BUFFER_SIZE];
            }
       }catch (MalformedURLException e){
           Log.v("test" , "bad URL");
       }catch (IOException e){
           Log.v("test" , "It's not a http connection");
       }
        return stringBuilder.toString();
    }

}

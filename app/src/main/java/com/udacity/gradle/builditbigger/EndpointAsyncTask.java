package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.example.jill.jokeactivitylib.JokeActivity;
import com.example.jill.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by jill on 1/11/16.
 */
//class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    public interface jokeTaskListener {
        void onJokeReceived(String joke);
        void onError();
    }

    private static MyApi myApiService = null;
    private Context context;
    private jokeTaskListener mCallback;


    public EndpointsAsyncTask(jokeTaskListener callback) {
        mCallback = callback;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
           // This one is for testing against local backend server
       /*     MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                }); */
            // This one is for testing against GoogleApps backend server
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbiggerbackend.appspot.com/_ah/api/");
                // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        if (mCallback == null)
            return;
        if((joke != null) && !joke.isEmpty()) {
            mCallback.onJokeReceived(joke);
        }
        else
            mCallback.onError();
    }
}

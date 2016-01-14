package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.JokeTeller;
import com.example.jill.jokeactivitylib.JokeActivity;

public class MainActivity extends AppCompatActivity
        implements FetchJokesTask.jokeTaskListener {

    public final String JOKE_EXTRA = "JOKE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fetchJokeFromEndpoints(View view) {
        new FetchJokesTask(this).execute();
    }

    @Override
    public void onJokeReceived(String joke) {
        Intent myIntent = new Intent(this, JokeActivity.class);
        myIntent.putExtra(JOKE_EXTRA, joke);
        startActivity(myIntent);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error retrieving joke from Google Endpoint", Toast.LENGTH_SHORT).show();
    }
}

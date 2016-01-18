package com.example.jill.jokeactivitylib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JokeActivity extends AppCompatActivity {
    public final String JOKE_EXTRA = "JOKE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String joke = bundle.getString(JOKE_EXTRA);
        setContentView(R.layout.activity_joke);
        JokeActivityFragment jokeFragment = (JokeActivityFragment) getSupportFragmentManager()
                .findFragmentById(R.id.joke_fragment);
        jokeFragment.displayJoke(joke);
    }
}
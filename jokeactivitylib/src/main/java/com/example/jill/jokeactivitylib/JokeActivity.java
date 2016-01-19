package com.example.jill.jokeactivitylib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class JokeActivity extends AppCompatActivity {
    public final String JOKE_EXTRA = "JOKE_EXTRA";
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String joke = bundle.getString(JOKE_EXTRA);
        setContentView(R.layout.activity_joke);
        JokeActivityFragment jokeFragment = (JokeActivityFragment) getSupportFragmentManager()
                .findFragmentById(R.id.joke_fragment);
        setupToolbar();
        jokeFragment.displayJoke(joke);
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Respond to the action bar's Up/Home button
            //NOTE that since this is inside a library, you
            //can't define PARENT_ACTIVITY in the manifest files,
            //since you don't know the name of the calling activity.
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    } */
}
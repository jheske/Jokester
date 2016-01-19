package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jill.jokeactivitylib.JokeActivity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 * https://developers.google.com/admob/android/interstitial
 * https://github.com/googleads/googleads-mobile-android-examples/tree/master/admob/InterstitialExample
 */
public class MainActivity extends AppCompatActivity
        implements FetchJokesTask.jokeTaskListener,IAdClosedListener {

    public String mJoke;
    public final String JOKE_EXTRA = "JOKE_EXTRA";
    private JokeHandler mJokeHandler;
    private ProgressBar mProgressSpinner;
    private Toolbar mToolbar;

    //https://developers.google.com/admob/android/interstitial
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();

        mProgressSpinner = (ProgressBar)findViewById(R.id.progressBar);
        mProgressSpinner.setVisibility(View.GONE);

        //All flavors have an JokeHandler. Free version loads an
        //ad while Paid version provides the event hooks but does nothing.
        mJokeHandler = new JokeHandler();
        //As per Android docs pre-load the ad for later display
        //Delegate to all flavors; free version loads an ad and
        //paid version doesn't so as not to waste time
        //loading ads user paid to remove
        mJokeHandler.loadInterstitialAd(this);
    }


    private void setupToolbar() {
        // Set a Toolbar to replace the ActionBar.
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
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
        mProgressSpinner.setVisibility(View.VISIBLE);
        mJokeHandler.getJoke(this);
        //new FetchJokesTask(this).execute(Jokes.JOKE_TYPE.BAD);
    }

    /**
     * Don't show the joke right away.  Give all flavors
     * of the app a chance to display an Interstitial ad (Free version)
     * or not (Paid version)
     *
     * @param joke
     */
    @Override
    public void onJokeReceived(String joke) {
        mJoke = joke;
        //showProgressBar
        mProgressSpinner.setVisibility(View.GONE);
        mJokeHandler.displayInterstitialAd();
    }

    @Override
    public void onError() {
        mProgressSpinner.setVisibility(View.GONE);
        Toast.makeText(this, "Error retrieving joke from Google Endpoint", Toast.LENGTH_SHORT).show();
    }

    /**
     * Finally, display the joke. Paid users will get a good joke,
     * while Free users get a crappy one.  Hey, it's a JOKE app,
     * after all!!
     */
    @Override
    public void onInterstitialAdClosed() {
        Intent myIntent = new Intent(this, JokeActivity.class);
        myIntent.putExtra(JOKE_EXTRA, mJoke);
        startActivity(myIntent);
    }
}

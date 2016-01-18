package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.view.View;

/**
 * Created by jill on 1/13/16.
 */

/**
 *
 * MainActivity uses an JokeHandler, which implements this class,
 * so all app flavors have to provide one.
 * Paid version doesn't have ads, so these methods are all no-ops.
 * This architecture keeps control flow in the MainActivity and lets
 * each flavor fill in its own details.
 *
 */
public interface JokeListener {
    public void getJoke(FetchJokesTask.jokeTaskListener callback);
    public void displayBannerAd(View root);
    public void loadInterstitialAd(Context context);
    public void displayInterstitialAd();
}

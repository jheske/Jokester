package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.Jokes;

import java.lang.Override;

public class JokeHandler implements JokeListener {
    IAdClosedListener mCallback;

    @Override
    public void getJoke(FetchJokesTask.jokeTaskListener callback) {
        new FetchJokesTask(callback).execute(Jokes.JOKE_TYPE.GOOD);
    }

    @Override
    public void loadInterstitialAd(Context context) {
        //no-op
        mCallback = (IAdClosedListener) context;
    }

    @Override
    public void displayBannerAd(View root) {
        //no-op
    }

    @Override
    public void displayInterstitialAd() {
        //Tell MainActivity we're done processing Interstitial Ad
        //so it can move right on to displaying the joke
        mCallback.onInterstitialAdClosed();
    }
}
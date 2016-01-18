package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.Jokes;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.lang.Override;

/**
 * This version displays ads.
 * https://github.com/googleads/googleads-mobile-android-examples/tree/master/admob/InterstitialExample
 */
public class JokeHandler implements JokeListener {
    private final static String TAG= "JokeListener";
    private final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";

    InterstitialAd mInterstitialAd;
    IAdClosedListener mCallback;


    @Override
    public void getJoke(FetchJokesTask.jokeTaskListener callback) {
        new FetchJokesTask(callback).execute(Jokes.JOKE_TYPE.BAD);
    }

    /**
     * Freebies get the banner ad.
     * @param root  View created by MainActivityFragment
     */
    @Override
    public void displayBannerAd(View root) {
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void loadInterstitialAd(Context context) {
        mCallback = (IAdClosedListener) context;
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(AD_UNIT_ID);
        mInterstitialAd.setAdListener(new AdListener() {
            //Done with this ad; load a new one for next time
            @Override
            public void onAdClosed() {
                requestNewInterstitialAd();
                mCallback.onInterstitialAdClosed();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d(TAG, "InterstitialAd load OK");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                Log.d(TAG, "InterstitialAd load FAILED with error code " + errorCode);
            }
        });
        requestNewInterstitialAd();
    }

    @Override
    public void displayInterstitialAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        else
            //If there's no ad, then notify the app so it can
            //display the joke anyway.  ??This will probably only happen
            //if there's no internet connection??
            mCallback.onInterstitialAdClosed();
      }

    public void requestNewInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
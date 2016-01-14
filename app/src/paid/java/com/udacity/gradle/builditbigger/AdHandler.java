package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.view.View;

import java.lang.Override;

public class AdHandler implements AdCallbackListener {
    IAdClosedListener mCallback;


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
package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.AdListener;
import com.udacity.gradle.builditbigger.R;

import java.lang.Override;

/**
 * This version displays ads.
 */
public class AdHandler implements AdListener {

    /**
     * Freebies get the banner ad.
     * @param root  View created by MainActivityFragment
     */
    @Override
    public void displayBannerAd(Context context, View root) {
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }
}
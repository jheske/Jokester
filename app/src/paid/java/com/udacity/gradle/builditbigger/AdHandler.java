package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.view.View;

import com.udacity.gradle.builditbigger.AdListener;
import com.udacity.gradle.builditbigger.Utils;
import java.lang.Override;


public class AdHandler implements AdListener {

    /**
     * Paid version doesn't have to see ads, so
     * this is a no-op,
     * but MainActivityFragment calls this function, so
     * it has to be provided.
     * @param root
     */
    @Override
    public void displayBannerAd(Context context, View root) {
        Utils.showToast(context,"Hooray, no ads!");
    }
}
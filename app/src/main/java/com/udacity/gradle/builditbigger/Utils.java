package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by jill on 1/14/16.
 */
public class Utils {
    static public boolean isNetworkAvailable(Context c) {
        ConnectivityManager cm =
                (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static void showToast(Context context,
                                 String message) {
        Toast.makeText(context,
                message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Make Utils a utility class by preventing instantiation.
     */
    private Utils() {
        throw new AssertionError();
    }

}

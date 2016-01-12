package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.test.UiThreadTest;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 * https://github.com/astuetz/build-it-bigger/blob/master/app/src/androidTest/java/com/udacity/gradle/builditbigger/GetJokeTaskTest.java
 * https://github.com/bdiegel/android-nano-p4/blob/master/app/src/androidTest/java/com/udacity/gradle/builditbigger/JokeAsyncTaskTest.java
 */
public class JokeAndroidTest extends AndroidTestCase implements EndpointsAsyncTask.jokeTaskListener {
    private final String TAG=getClass().getSimpleName();
    CountDownLatch signal;
    EndpointsAsyncTask jokeTask;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);
        jokeTask = new EndpointsAsyncTask(this);
    }

    @UiThreadTest
    public void testGetJoke() throws InterruptedException {
        new EndpointsAsyncTask(this).execute();
        signal.await(30, TimeUnit.SECONDS);
    }

    @Override
    public void onJokeReceived(String joke) {
        Log.d(TAG, "onJokeReceived");
        assertTrue("Error: Joke string is empty!!",!joke.isEmpty());
        assertNotNull("Error: This is no joke!",joke);
        signal.countDown();
    }

    @Override
    public void onError() {
        fail("Error fetching joke from Endpoints");
        signal.countDown();
    }
}
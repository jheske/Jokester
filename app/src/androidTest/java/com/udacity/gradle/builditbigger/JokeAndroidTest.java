package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.test.UiThreadTest;
import android.util.Log;

import com.example.Jokes;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class JokeAndroidTest extends AndroidTestCase
        implements FetchJokesTask.jokeTaskListener {
    private final String TAG=getClass().getSimpleName();
    CountDownLatch signal;
    FetchJokesTask jokeTask;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);
        jokeTask = new FetchJokesTask(this);
    }

    @UiThreadTest
    public void testGetJoke() throws InterruptedException {
        new FetchJokesTask(this).execute(Jokes.JOKE_TYPE.BAD);
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
package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jill on 1/18/16.
 */
public class Jokes {
    public enum JOKE_TYPE {GOOD,BAD};

    private List<String> mJokes = new ArrayList<>();
    private List<String> mGoodJokes = new ArrayList<>();
    private List<String> mBadJokes = new ArrayList<>();
    Random mRand = new Random();

    public Jokes() {
        initJokes();
    }

    private void initJokes() {
        mGoodJokes.add("Good joke 0");
        mGoodJokes.add("Good joke 1");
        mGoodJokes.add("Good joke 2");
        mGoodJokes.add("Good joke 3");
        mBadJokes.add("Bad joke 0");
        mBadJokes.add("Bad joke 1");
        mBadJokes.add("Bad joke 2");
        mBadJokes.add("Bad joke 3");
    }

    public String getJoke(JOKE_TYPE jokeType) {
        if (jokeType == JOKE_TYPE.GOOD)
            return getGoodJoke();
        else
            return getBadJoke();
    }

    public String getGoodJoke() {
        return (mGoodJokes.get(mRand.nextInt(mGoodJokes.size())));
    }

    public String getBadJoke() {
        return (mBadJokes.get(mRand.nextInt(mBadJokes.size())));
    }
}

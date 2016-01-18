package com.example;

import java.util.List;

public class JokeTeller {
    private Jokes mJokes = new Jokes();

    /**
     * Send back a good joke or a bad one.
     *
     * The app will choose which one to display depending
     * on whether it's the paid or free version.
     *
     * @return
     */
    public String getJoke(Jokes.JOKE_TYPE jokeType) {
        return mJokes.getJoke(jokeType);
    }
}

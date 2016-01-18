package com.example;

import java.util.List;

public class JokeTeller {
    private Jokes mJokes = new Jokes();

    /**
     * Return a good joke or a bad one.
     */
    public String getJoke(Jokes.JOKE_TYPE jokeType) {
        return mJokes.getJoke(jokeType);
    }
}

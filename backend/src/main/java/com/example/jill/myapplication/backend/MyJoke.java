package com.example.jill.myapplication.backend;

import com.example.Jokes;

/** The object model for the data we are sending through endpoints */
public class MyJoke {
    private Jokes.JOKE_TYPE mJokeType;
    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }

    public void setJokeType(Jokes.JOKE_TYPE jokeType) {
        mJokeType = jokeType;
    }
}
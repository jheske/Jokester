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
        mGoodJokes.add("Want to hear a word I just made up? Plagiarism!");
        mGoodJokes.add("Getting paid to sleep would be a dream job.");
        mGoodJokes.add("Wrinkles should merely indicate where smiles have been.\n --Mark Twain");
        mGoodJokes.add("Age is an issue of mind over matter. If you don't mind, it doesn't matter.\n --Mark Twain");
        mGoodJokes.add("Words cannot express how limited my vocabulary is");
        mGoodJokes.add("Mitt Romney’s email was hacked! So if you start getting messages that sound like they’re from a bot, he’s fixed the problem.\n --Stephen Colbert");
        mGoodJokes.add("Folks, I don’t trust children. They’re here to replace us. --Stephen Colbert");
        mGoodJokes.add("I changed my password to 'incorrect.' So whenever I forget what it is the computer will say 'Your password is incorrect.'");
        mBadJokes.add("Why did the boy drop his ice cream?\n" + "Because he was hit by a bus.");
        mBadJokes.add("What's stucco? It's what happens when you step in bubblegummo.");
        mBadJokes.add("What is the Karate experts favorite beverage? Kara-tea.");
        mBadJokes.add("Where do Floridians wash their clothes? In Fort Launderdale.");
        mBadJokes.add("Knock Knock. Who’s there? The Truth. No joke. --Stephen Colbert");
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

package com.example.jill.jokeactivitylib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {
    TextView tvJokeText;

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        tvJokeText = (TextView)rootView.findViewById(R.id.tv_joke_text);
        return rootView;
    }

    public void displayJoke(String joke) {
        tvJokeText.setText(joke);
    }
}

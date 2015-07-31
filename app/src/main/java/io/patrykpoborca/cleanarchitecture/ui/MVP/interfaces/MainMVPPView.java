package io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces;

import java.util.ArrayList;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.PView;

/**
 * Created by Patryk on 7/28/2015.
 */
public interface MainMVPPView extends PView {
    public void displayFetchedTweet(String tweet);

    public void displayPreviousTweets(ArrayList<String> list);
}

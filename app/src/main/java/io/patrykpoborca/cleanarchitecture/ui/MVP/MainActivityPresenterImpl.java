package io.patrykpoborca.cleanarchitecture.ui.MVP;

import android.os.Handler;

import io.patrykpoborca.cleanarchitecture.network.TwitterApi;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainActivityPView;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainActivityPresenter;

/**
 * Created by Patryk on 7/28/2015.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {

    private final TwitterApi twitterApi;

    private final Handler handler;
    private MainActivityPView mainActivityPView;

    public MainActivityPresenterImpl(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
        this.handler = new Handler();
    }

    @Override
    public void registerView(MainActivityPView activity) {
        this.mainActivityPView = activity;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void fetchTweet() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainActivityPView.displayFetchedTweet(twitterApi.getTweet());
            }
        }, 2000);
    }

    @Override
    public void fetchTwoTweets() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainActivityPView.displayPreviousTweets(twitterApi.fetchXrecents(2));
            }
        }, 2000);
    }
}

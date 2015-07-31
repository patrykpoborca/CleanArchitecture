package io.patrykpoborca.cleanarchitecture.ui.MVP;

import android.os.Handler;

import io.patrykpoborca.cleanarchitecture.network.TwitterApi;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPView;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPresenter;

/**
 * Created by Patryk on 7/28/2015.
 */
public class MainMVPPresenterImpl implements MainMVPPresenter {

    private final TwitterApi twitterApi;

    private final Handler handler;
    private MainMVPPView mMainMVPPView;

    public MainMVPPresenterImpl(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
        this.handler = new Handler();
    }

    @Override
    public void registerView(MainMVPPView activity) {
        this.mMainMVPPView = activity;
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
                mMainMVPPView.displayFetchedTweet(twitterApi.getTweet());
            }
        }, 2000);
    }

    @Override
    public void fetchTwoTweets() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainMVPPView.displayPreviousTweets(twitterApi.fetchXrecents(2));
            }
        }, 2000);
    }
}

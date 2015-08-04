package io.patrykpoborca.cleanarchitecture.ui.MVP;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.network.TwitterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPView;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPresenter;
import rx.Observable;
import rx.Subscription;
import rx.subjects.PublishSubject;

/**
 * Created by Patryk on 7/28/2015.
 */
public class MainMVPPresenterImpl implements MainMVPPresenter {

    private static final int TWEET_COUNT = 2;
    private final TwitterApi twitterApi;
    private final Retrofit retrofit;
    private MainMVPPView mainMVPView;
    private boolean loggedIn = false;


    public MainMVPPresenterImpl(TwitterApi twitterApi, Retrofit retrofit) {
        this.twitterApi = twitterApi;
        this.retrofit = retrofit;
    }

    @Override
    public void registerView(MainMVPPView activity) {
        this.mainMVPView = activity;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void fetchCurrentTweet() {
        twitterApi.getTweet().subscribe(s -> this.mainMVPView.displayFetchedTweet(s));
    }

    @Override
    public void fetchPreviousTweets() {
        final List<String> tweets = new ArrayList<>();
        final Subscription twitterSub = twitterApi.fetchXrecents(TWEET_COUNT)
                .map(s -> {
                    tweets.add(s);
                    if(tweets.size() == TWEET_COUNT){
                        mainMVPView.displayToast("Tweet size exceeded " + TWEET_COUNT);
                    }
                    return tweets;
                })
                .filter(l -> l.size() == TWEET_COUNT)
                .subscribe(l -> mainMVPView.displayPreviousTweets(l));
    }

    @Override
    public void toggleLogin(String userName, String userPassword) {
        if(loggedIn){
            mainMVPView.setUserButtonText("Login");
            mainMVPView.displayToast("User logged out");
        }
        else {
            this.retrofit.performRequest(userName, userPassword)
                    .subscribe(userProfile -> {
                        mainMVPView.displayToast(userProfile.getFormattedCredentials() + " Logged in");
                        mainMVPView.setUserButtonText("Log " + userProfile.getUserName() + " out");
                    });
        }
        this.loggedIn = !loggedIn;
    }
}

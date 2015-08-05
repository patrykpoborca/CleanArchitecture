package io.patrykpoborca.cleanarchitecture.ui.MVP;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.network.TwitterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPView;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPresenter;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
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
    private int tweetsAdded = 0;


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
        mainMVPView.toggleProgressBar(true);
        twitterApi.getTweet().subscribe(s -> {
            mainMVPView.toggleProgressBar(false);
            tweetsAdded ++;
            if(tweetsAdded > TWEET_COUNT){
                mainMVPView.displayToast("Tweet size exceeded " + TWEET_COUNT);
            }
            this.mainMVPView.displayFetchedTweet(s);
        });
    }

    @Override
    public void fetchPreviousTweets() {

        mainMVPView.toggleProgressBar(true);
        final Subscription twitterSub = twitterApi.fetchXrecents(TWEET_COUNT)
                .subscribe(l -> {
                    mainMVPView.displayPreviousTweets(l);
                    mainMVPView.toggleProgressBar(false);
                });
    }

    @Override
    public void toggleLogin(String userName, String userPassword) {
        mainMVPView.toggleProgressBar(true);
        if(loggedIn){
            Observable.just(null)
                    .delay(2, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        mainMVPView.toggleProgressBar(false);
                        mainMVPView.setUserButtonText("Login");
                        mainMVPView.displayToast("User logged out");
                        mainMVPView.toggleLoginContainer(true);
                        //could implement more literal less reusable methods, such as loggedIn and loggedOut such as in the MVPIC example.
                        //However I wanted to be extremely verbose in the MVP example.
                    });
        }
        else {
            this.retrofit.performRequest(userName, userPassword)
                    .subscribe(userProfile -> {
                        mainMVPView.displayToast(userProfile.getFormattedCredentials() + " Logged in");
                        mainMVPView.setUserButtonText("Log " + userProfile.getUserName() + " out");
                        mainMVPView.toggleProgressBar(false);
                        mainMVPView.toggleLoginContainer(false);
                    });
        }
        this.loggedIn = !loggedIn;
    }
}

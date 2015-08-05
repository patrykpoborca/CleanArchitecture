package io.patrykpoborca.cleanarchitecture.ui.MVVM;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.network.TwitterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import io.patrykpoborca.cleanarchitecture.ui.MVVM.base.BaseViewModel;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;

public class MainViewModel extends BaseViewModel{

    private static final int TWEET_COUNT = 2;
    private final TwitterApi twitterApi;
    private final Retrofit retroFit;
    private boolean loggedIn = false;
    private int tweetsAdded = 0;
    private PublishSubject<String> messageStream;


    @Inject
    public MainViewModel(TwitterApi api, Retrofit retrofit){
        this.twitterApi = api;
        this.retroFit = retrofit;
    }

    public Observable<String> fetchCurrentTweet(){
        tweetsAdded ++;
        if(tweetsAdded > TWEET_COUNT){
            messageStream.onNext("Tweet size exceeded " + TWEET_COUNT);
        }

        return twitterApi.getTweet();
    }


    public Observable<List<String>> fetchPreviousTweets(){
        return twitterApi.fetchXrecents(2);
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public Observable<UserProfile> toggleLogin(String userName, String password){
        loggedIn = !loggedIn;
        if(loggedIn) {
            return this.retroFit.performRequest(userName, password);
        }
        else{
            UserProfile profile = null;
            return Observable.just(profile)
                    .delay(2, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    public Observable<String> getMessageStream() {
        if(messageStream == null){
            messageStream = PublishSubject.<String>create();
        }
        return messageStream.asObservable();
    }
}

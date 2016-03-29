package io.patrykpoborca.cleanarchitecture.ui.MVVM;

import java.util.List;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.models.UserProfile;
import io.patrykpoborca.cleanarchitecture.ui.MVVM.base.BaseViewModel;
import rx.Observable;
import rx.subjects.PublishSubject;

public class MainViewModel extends BaseViewModel {

    private static final int TWEET_COUNT = 2;
    private final TweeterApi tweeterApi;
    private final Retrofit retroFit;
    private boolean loggedIn = false;
    private int tweetsAdded = 0;
    private PublishSubject<String> messageStream;


    @Inject
    public MainViewModel(TweeterApi api, Retrofit retrofit){
        this.tweeterApi = api;
        this.retroFit = retrofit;
    }

    public Observable<String> fetchCurrentTweet(){
        tweetsAdded ++;

        Observable<String> observable=  tweeterApi.getTweet();

        if(tweetsAdded > TWEET_COUNT){
            observable.subscribe(s ->  messageStream.onNext("Tweet size exceeded " + TWEET_COUNT));
        }
        return observable;
    }


    public Observable<List<String>> fetchPreviousTweets(){
        return tweeterApi.fetchXrecents(2);
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public Observable<UserProfile> toggleLogin(String userName, String password){
        loggedIn = !loggedIn;
        if(loggedIn) {
            return this.tweeterApi.login(userName, password);
        }
        else{
            UserProfile profile = null;
            return tweeterApi.logout()
                    .map(o -> profile);
        }
    }

    public Observable<String> getMessageStream() {
        if(messageStream == null){
            messageStream = PublishSubject.<String>create();
        }
        return messageStream.asObservable();
    }

    public Observable<String> loadWebPage(String url){
        return retroFit.fetchSomePage(url);
    }
}

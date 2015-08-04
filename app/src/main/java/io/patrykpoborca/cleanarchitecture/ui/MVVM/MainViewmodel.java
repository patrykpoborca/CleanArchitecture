package io.patrykpoborca.cleanarchitecture.ui.MVVM;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.network.TwitterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import io.patrykpoborca.cleanarchitecture.ui.MVVM.base.BaseViewModel;
import rx.Observable;

public class MainViewModel extends BaseViewModel{

    private static final int TWEET_SIZE = 2;
    private final TwitterApi twitterApi;
    private final Retrofit retroFit;


    @Inject
    public MainViewModel(TwitterApi api, Retrofit retrofit){
        this.twitterApi = api;
        this.retroFit = retrofit;
    }

    public Observable<String> fetchCurrentTweet(){
        return twitterApi.getTweet();
    }


    public Observable<List<String>> fetchPreviousTweets(){
        final List<String> list =new ArrayList<>(TWEET_SIZE);
        return twitterApi.fetchXrecents(2)
                    .map(s -> {
                        list.add(s);
                        return list;
                    })
                    .filter(l -> list.size() == TWEET_SIZE);
    }

    public Observable<UserProfile> login(String userName, String password){
        return this.retroFit.performRequest(userName, password);
    }
}

package io.patrykpoborca.cleanarchitecture.network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.models.UserProfile;
import io.patrykpoborca.cleanarchitecture.util.Constants;
import rx.Observable;
import rx.Scheduler;


public class TweeterApi {

    protected final Scheduler mainScheduler;
    Retrofit retrofit;
    LocalDataCache localDataCache;
    private UserProfile userName;

    @Inject
    public TweeterApi(Retrofit retro, LocalDataCache cache, @Named(Constants.MAIN_THREAD) Scheduler mainScheduler) {
        this.localDataCache = cache;
        this.retrofit = retro;
        this.mainScheduler = mainScheduler;
    }

    public Observable<String> getTweet(){
        return retrofit.completeRequest()
                .map(tweet -> {
                    localDataCache.saveTweet(tweet);

                    if (isLoggedIn()) {
                        tweet = userName.getUserName() + " -> " + tweet;
                    }
                    else {
                        tweet = "Some user -> " + tweet;
                    }

                    return tweet;
                })
                .observeOn(mainScheduler);
    }

    public Observable<List<String>> fetchXrecents(int count){

        return localDataCache.fetchRecentTweets()
                .map(list ->{
                    List<String> tweets = new ArrayList<>(count);
                    int size = list.size() <= count ? list.size() : count;
                    for(int i=list.size() -1; i >= 0 && size > tweets.size(); i--){
                        tweets.add(list.get(i));
                    }
                    return tweets;
                })
                .observeOn(mainScheduler);
    }

    public Observable<UserProfile> login(String username, String password) {

        Observable<UserProfile> observable = Observable.just(new UserProfile(username, password))
                .delay(2, TimeUnit.SECONDS)
                .observeOn(mainScheduler);
        
        observable.subscribe(user -> this.userName = user);
        return observable;
    }

    public Observable<Object> logout(){
        userName = null;
        return Observable.just(null)
                .delay(2, TimeUnit.SECONDS)
                .observeOn(mainScheduler);
    }

    public boolean isLoggedIn(){
        return this.userName != null;
    }

}

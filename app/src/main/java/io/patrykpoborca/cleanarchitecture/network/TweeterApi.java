package io.patrykpoborca.cleanarchitecture.network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.models.UserProfile;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Patryk on 7/27/2015.
 */
public class TweeterApi {

    Retrofit retrofit;
    LocalDataCache localDataCache;
    private UserProfile userName;

    @Inject
    public TweeterApi(Retrofit retro, LocalDataCache cache) {
        this.localDataCache = cache;
        this.retrofit = retro;
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
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<String>> fetchXrecents(int count){

        return localDataCache.fetchRecentTweets()
                .map(list ->{
                    List<String> tweets = new ArrayList<>(count);
                    int size = list.size() <= count ? list.size() : count;
                    for(int i=0; i < size; i++){
                        tweets.add(list.get(i));
                    }
                    return tweets;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<UserProfile> login(String username, String password) {

        Observable<UserProfile> observable = Observable.just(new UserProfile(username, password))
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
        
        observable.subscribe(user -> this.userName = user);
        return observable;
    }

    public Observable<Object> logout(){
        userName = null;
        return Observable.just(null)
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public boolean isLoggedIn(){
        return this.userName != null;
    }
}

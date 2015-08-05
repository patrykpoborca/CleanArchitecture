package io.patrykpoborca.cleanarchitecture.network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
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
        String tweet;
        if(isLoggedIn()){
            tweet = userName.getUserName() + " -> " + retrofit.completeRequest();
        }
        else {
            tweet = "Some user -> " + retrofit.completeRequest();
        }
        localDataCache.saveTweet(tweet);

        return Observable.just(tweet)
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<String>> fetchXrecents(int some){
        List<String> someTweets = new ArrayList(some);
        int size = some > localDataCache.fetchRecentTweets().size() ? localDataCache.fetchRecentTweets().size() : some;

        for(int i= 0; i < size; i++){
            someTweets.add(localDataCache.fetchRecentTweets().get(localDataCache.fetchRecentTweets().size() - i -1));
        }

        return Observable.just(someTweets)
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<UserProfile> login(String username, String password) {

        Observable<UserProfile> observable = Observable.just(new UserProfile(username, password))
                .subscribeOn(Schedulers.io())
                .delay(3, TimeUnit.SECONDS)
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

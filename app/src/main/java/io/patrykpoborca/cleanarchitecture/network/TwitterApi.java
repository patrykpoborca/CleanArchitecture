package io.patrykpoborca.cleanarchitecture.network;

import java.util.ArrayList;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;

/**
 * Created by Patryk on 7/27/2015.
 */
public class TwitterApi {

    Retrofit retrofit;
    LocalDataCache localDataCache;

    public TwitterApi(Retrofit retro, LocalDataCache cache) {
        this.localDataCache = cache;
        this.retrofit = retro;
    }

    public String getTweet(){
        String tweet = "Someone Tweeted this -> " + retrofit.parseString();
        localDataCache.saveTweet(tweet);
        return tweet;
    }

    public ArrayList<String> fetchXrecents(int some){
        ArrayList<String> someTweets = new ArrayList(some);
        int size = some > localDataCache.fetchRecentTweets().size() ? localDataCache.fetchRecentTweets().size() : some;

        for(int i= 0; i < size; i++){
            someTweets.add(localDataCache.fetchRecentTweets().get(localDataCache.fetchRecentTweets().size() - i -1));
        }

        return  someTweets;
    }
}

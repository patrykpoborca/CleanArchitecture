package io.patrykpoborca.cleanarchitecture.localdata;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Patryk on 7/27/2015.
 */
public class LocalDataCache {

    //pretend this is some read/write to disk :)
    private static ArrayList<String> sPastTweets;
    private Context context;

    public LocalDataCache(Context context) {
        this.context = context;
        sPastTweets = new ArrayList<>();
    }

    public void saveTweet(String tweet){
        sPastTweets.add(tweet);
    }

    public ArrayList<String> fetchRecentTweets(){
        return sPastTweets;
    }
}

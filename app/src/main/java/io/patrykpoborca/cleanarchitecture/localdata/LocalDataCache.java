package io.patrykpoborca.cleanarchitecture.localdata;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;


public class LocalDataCache {

    //pretend this is some read/write to disk :)
    protected static ArrayList<String> sPastTweets;
    private Context context;

    public LocalDataCache(Context context) {
        this.context = context;
        sPastTweets = new ArrayList<>();
    }

    public void saveTweet(String tweet) {
        sPastTweets.add(tweet);
    }

    public Observable<List<String>> fetchRecentTweets() {
        return Observable.just(sPastTweets)
                .map(arrayList -> {
                    List<String> list = arrayList;
                    return list;
                })
                .delay(2, TimeUnit.SECONDS);
    }
}

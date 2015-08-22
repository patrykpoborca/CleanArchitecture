package io.patrykpoborca.cleanarchitecture.mockimpl;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import rx.Observable;

public class MockLocalDataCache extends LocalDataCache {

    public MockLocalDataCache(Context context) {
        super(context);
    }

    /**
     *  No mock-impl needed for our scenario here.. see MockOKHttp/MockRetrofit
     */
    @Override
    public void saveTweet(String tweet) {
        sPastTweets.add(tweet);
    }

    @Override
    public Observable<List<String>> fetchRecentTweets() {
        return Observable.just(sPastTweets)
                .map(arrayList -> {
                    List<String> list = arrayList;
                    return list;
                });
    }
}

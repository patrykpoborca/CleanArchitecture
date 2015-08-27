package io.patrykpoborca.cleanarchitecture.mockimpl;

import android.content.Context;

import java.util.List;

import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import rx.Observable;

public class MockLocalDataCache extends LocalDataCache {

    public MockLocalDataCache(Context context) {
        super(context);
    }

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

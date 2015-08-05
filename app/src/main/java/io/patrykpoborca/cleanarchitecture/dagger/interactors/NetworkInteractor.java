package io.patrykpoborca.cleanarchitecture.dagger.interactors;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.dagger.interactors.base.BaseInteractor;
import io.patrykpoborca.cleanarchitecture.network.TwitterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import rx.Observable;

/**
 * Created by Patryk on 7/29/2015.
 */
public class NetworkInteractor extends BaseInteractor {


    private final Retrofit retrofit;
    private final TwitterApi twitterAPI;

    @Inject
    public NetworkInteractor(Retrofit retrofit, TwitterApi api){
        this.retrofit = retrofit;
        this.twitterAPI = api;
    }

    public Observable<UserProfile> attemptLogin(String username, String password) {
        return retrofit.performRequest(username, password);
    }

    public Observable<String> fetchTweet() {
        return twitterAPI.getTweet();
    }

    public Observable<List<String>> fetchTweets(int count) {
        return twitterAPI.fetchXrecents(count);
    }
}

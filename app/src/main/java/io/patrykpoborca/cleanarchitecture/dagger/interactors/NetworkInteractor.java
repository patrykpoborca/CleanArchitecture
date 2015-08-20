package io.patrykpoborca.cleanarchitecture.dagger.interactors;

import java.util.List;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.dagger.interactors.base.BaseInteractor;
import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.models.UserProfile;
import rx.Observable;

/**
 * Created by Patryk on 7/29/2015.
 */
public class NetworkInteractor extends BaseInteractor {


    private final Retrofit retrofit;
    private final TweeterApi tweeterAPI;

    @Inject
    public NetworkInteractor(Retrofit retrofit, TweeterApi api){
        this.retrofit = retrofit;
        this.tweeterAPI = api;
    }

    public Observable<UserProfile> attemptLogin(String username, String password) {
        return tweeterAPI.login(username, password);
    }

    public Observable<String> fetchTweet() {
        return tweeterAPI.getTweet();
    }

    public Observable<List<String>> fetchTweets(int count) {
        return tweeterAPI.fetchXrecents(count);
    }

    public Observable<String> loadWebpage(String url){
        return retrofit.fetchSomePage(url);
    }
}

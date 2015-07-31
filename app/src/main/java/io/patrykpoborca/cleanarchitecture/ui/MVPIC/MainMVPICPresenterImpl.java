package io.patrykpoborca.cleanarchitecture.ui.MVPIC;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.network.TwitterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces.MainActivityMVPICPview;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces.MainMVPICPresenter;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Patryk on 7/29/2015.
 */
public class MainMVPICPresenterImpl implements MainMVPICPresenter {

    private final Retrofit retrofit;
    private final TwitterApi twitterAPI;
    private MainActivityMVPICPview view;


    public MainMVPICPresenterImpl(Retrofit retrofit, TwitterApi api){
        this.twitterAPI = api;
        this.retrofit = retrofit;
    }

    @Override
    public void registerView(MainActivityMVPICPview view) {
        this.view = view;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public Observable<String> fetchCurrentTweet() {
        return Observable.just(twitterAPI.getTweet())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .on
                .delay(1, TimeUnit.SECONDS);
    }

    @Override
    public Observable<String> fetchPreviousTweets() {
        return Observable.from(twitterAPI.fetchXrecents(2))
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<UserProfile> logUserIn(){
        return retrofit.performRequest("someone", "somepassword");
    }
}

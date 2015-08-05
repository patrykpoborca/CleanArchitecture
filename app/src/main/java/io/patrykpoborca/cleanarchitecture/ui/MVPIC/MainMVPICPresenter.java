package io.patrykpoborca.cleanarchitecture.ui.MVPIC;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.dagger.interactors.NetworkInteractor;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.base.BasePresenterMVPIC;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces.MainActivityMVPICPview;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Patryk on 7/29/2015.
 */
public class MainMVPICPresenter extends BasePresenterMVPIC<MainActivityMVPICPview> {

    private static final int TWEET_COUNT = 2;
    private final NetworkInteractor interactor;
    private boolean loggedIn = false;
    private int tweetsAdded = 0;


    @Inject
    public MainMVPICPresenter(NetworkInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onAttach() {
        super.onAttach();
    }

    @Override
    public void onDettach() {
        super.onDettach();
    }

    public Observable<String> fetchCurrentTweet() {
        getPView().toggleProgressBar(true);

        Observable<String> observable = interactor.fetchTweet();
        observable.subscribe(s -> {
            getPView().toggleProgressBar(false);
            tweetsAdded++;
            if (tweetsAdded > TWEET_COUNT) {
                getPView().displayToast("Tweet size exceeded " + TWEET_COUNT);
            }
        });
        return observable;
    }
    
    public Observable<List<String>> fetchPreviousTweets() {
        getPView().toggleProgressBar(true);

        Observable<List<String>> observable = interactor.fetchTweets(TWEET_COUNT);
        observable.subscribe(l -> {
            getPView().toggleProgressBar(false);
        });
        return observable;
    }
    
    public void toggleLogin(String userName, String password){

        getPView().toggleProgressBar(true);
        if(!loggedIn) {
            loggedIn = true;
            Observable<UserProfile> observable = interactor.attemptLogin(userName, password);
            observable.subscribe(p->
            {
                getPView().toggleProgressBar(false);
                getPView().loggedIn(p);
            });

        }
        else{
            UserProfile profile = null;
            loggedIn = false;
            //avoid relying on timer task/handler
            Observable.just(null).delay(2, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        getPView().loggedOut();
                        getPView().toggleProgressBar(false);
                    });
        }
    }
}

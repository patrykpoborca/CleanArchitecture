package io.patrykpoborca.cleanarchitecture.ui.MVPCI;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.patrykpoborca.cleanarchitecture.dagger.interactors.NetworkInteractor;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.base.BasePresenterMVPCI;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.interfaces.MainActivityMVPCIPview;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.models.UserProfile;
import io.patrykpoborca.cleanarchitecture.util.Constants;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Patryk on 7/29/2015.
 */
public class MainMVPCIPresenter extends BasePresenterMVPCI<MainActivityMVPCIPview> {

    private static final int TWEET_COUNT = 2;
    private final NetworkInteractor interactor;
    private final Scheduler mainScheduler;
    private boolean loggedIn = false;
    private int tweetsAdded = 0;


    @Inject
    public MainMVPCIPresenter(NetworkInteractor interactor, @Named(Constants.MAIN_THREAD) Scheduler mainScheduler) {
        this.interactor = interactor;
        this.mainScheduler = mainScheduler;
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
            interactor.logout()
                    .observeOn(mainScheduler)
                    .subscribe(s -> {
                        getPView().loggedOut();
                        getPView().toggleProgressBar(false);
                    });
        }
    }

    public Observable<String> loadWebPage(String url){
        getPView().toggleProgressBar(true);
        Observable<String> observable = interactor.loadWebpage(url);

        observable.subscribe(s -> getPView().toggleProgressBar(false));
        return observable;
    }
}

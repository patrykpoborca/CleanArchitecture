package io.patrykpoborca.cleanarchitecture.ui.MVPIC;

import java.util.List;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.dagger.interactors.NetworkInteractor;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.base.BasePresenterMVPIC;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces.MainActivityMVPICPview;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import rx.Observable;

/**
 * Created by Patryk on 7/29/2015.
 */
public class MainMVPICPresenter extends BasePresenterMVPIC {

    private static final int TWEET_COUNT = 2;
    private final NetworkInteractor interactor;
    private MainActivityMVPICPview pView;


    @Inject
    public MainMVPICPresenter(NetworkInteractor interactor) {
        this.interactor = interactor;
    }

    @Inject
    public void registerView(MainActivityMVPIC view) {
        this.pView = view;
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
        return interactor.fetchTweet();
    }
    
    public Observable<List<String>> fetchPreviousTweets() {
        Observable<List<String>> observable = interactor.fetchTweets(TWEET_COUNT);
        observable.subscribe(l -> {
            if(l.size() == TWEET_COUNT){
                //Supervising controller returns observables allowing the view to react to them, however
                // business logic such as this arbitrary call should be determined by the supervising controller
                pView.weHaveExceeded3TweetsOnBackend();
            }
        });
        return observable;
    }
    
    public Observable<UserProfile> logUserIn(){
        return interactor.attemptLogin("someone", "somepassword");
    }
}

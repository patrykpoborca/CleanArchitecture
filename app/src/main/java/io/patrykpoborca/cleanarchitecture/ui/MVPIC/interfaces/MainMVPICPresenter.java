package io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces;

import java.util.List;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.Presenter;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.MainActivityMVPIC;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import rx.Observable;

/**
 * Created by Patryk on 7/28/2015.
 */
public interface MainMVPICPresenter extends Presenter<MainActivityMVPICPview>{



    public Observable<String> fetchCurrentTweet();

    public Observable<String> fetchPreviousTweets();

    public Observable<UserProfile> logUserIn();
}

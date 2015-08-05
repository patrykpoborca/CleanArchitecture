package io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.Presenter;

/**
 * Created by Patryk on 7/28/2015.
 */
    public interface MainMVPPresenter extends Presenter<MainMVPPView> {

    public void fetchCurrentTweet();

    public void fetchPreviousTweets();

    public void toggleLogin(String userName, String userPassword);
}

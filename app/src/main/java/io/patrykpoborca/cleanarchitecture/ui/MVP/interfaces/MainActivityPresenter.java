package io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.Presenter;

/**
 * Created by Patryk on 7/28/2015.
 */
public interface MainActivityPresenter extends Presenter<MainActivityPView> {

    public void fetchTweet();

    public void fetchTwoTweets();
}

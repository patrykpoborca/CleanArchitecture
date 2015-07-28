package io.patrykpoborca.cleanarchitecture.dagger.modules;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.network.TwitterApi;
import io.patrykpoborca.cleanarchitecture.ui.MVP.MainActivityPresenterImpl;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainActivityPresenter;

/**
 * Created by Patryk on 7/28/2015.
 */
@Module
public class PresenterModule {

    @Provides
    MainActivityPresenter providesMainActivityPresenter(TwitterApi twitterApi){
        return new MainActivityPresenterImpl(twitterApi);
    }
}

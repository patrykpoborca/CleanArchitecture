package io.patrykpoborca.cleanarchitecture.dagger.modules;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.network.TwitterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVP.MainMVPPresenterImpl;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPresenter;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.MainMVPICPresenterImpl;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces.MainMVPICPresenter;

/**
 * Created by Patryk on 7/28/2015.
 */
@Module
public class PresenterModule {

    @Provides
    MainMVPPresenter providesMainActivityPresenter(TwitterApi twitterApi){
        return new MainMVPPresenterImpl(twitterApi);
    }

    @Provides
    MainMVPICPresenter providesMainMVPICPresenter(TwitterApi api, Retrofit retrofit){
        return new MainMVPICPresenterImpl(retrofit, api);
    }
}

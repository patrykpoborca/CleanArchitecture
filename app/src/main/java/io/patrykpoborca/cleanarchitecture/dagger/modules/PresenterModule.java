package io.patrykpoborca.cleanarchitecture.dagger.modules;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;
import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVP.TweeterMVPPresenterImpl;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.TweeterMVPPresenter;

@Module
public class PresenterModule {

    @Provides
    @ActivityScope
    TweeterMVPPresenter providesMainPresenter(TweeterApi api, Retrofit retrofit){
        return new TweeterMVPPresenterImpl(api, retrofit);
    }
}

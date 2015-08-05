package io.patrykpoborca.cleanarchitecture.dagger.modules;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;
import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVP.MainMVPPresenterImpl;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPresenter;

@Module
public class PresenterModule {

    @Provides
    @ActivityScope
    MainMVPPresenter providesMainPresenter(TweeterApi api, Retrofit retrofit){
        return new MainMVPPresenterImpl(api, retrofit);
    }
}

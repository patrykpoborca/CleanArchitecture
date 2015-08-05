package io.patrykpoborca.cleanarchitecture.dagger.modules;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.interactors.NetworkInteractor;
import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;

/**
 * Created by Patryk on 7/31/2015.
 */
@Module
public class InteractorModule {

    @Provides
    NetworkInteractor providesNetworkInteractor(Retrofit retrofit, TweeterApi api){
        return new NetworkInteractor(retrofit, api);
    }
}

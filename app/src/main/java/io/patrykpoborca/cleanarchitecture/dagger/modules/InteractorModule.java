package io.patrykpoborca.cleanarchitecture.dagger.modules;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.NetworkInteractorImpl;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces.NetworkInteractor;

/**
 * Created by Patryk on 7/31/2015.
 */
@Module
public class InteractorModule {

    @Provides
    NetworkInteractor providesNetworkInteractor(Retrofit retrofit){
        return new NetworkInteractorImpl(retrofit);
    }
}

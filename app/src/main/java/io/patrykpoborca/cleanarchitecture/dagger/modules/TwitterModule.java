package io.patrykpoborca.cleanarchitecture.dagger.modules;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ApplicationScope;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ExposedAPIScope;
import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;

/**
 * Created by Patryk on 7/27/2015.
 */
@Module
public class TwitterModule {

    @Provides
    @ExposedAPIScope
    TweeterApi providesTweeterAPI(Retrofit retro, LocalDataCache cache){
        //technically speaking we can have an injectable constructor for the tweeterApi, but
        return new TweeterApi(retro, cache);
    }
}

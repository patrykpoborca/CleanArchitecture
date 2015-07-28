package io.patrykpoborca.cleanarchitecture.dagger.modules;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.TwitterApi;
import io.patrykpoborca.cleanarchitecture.network.base.OKHttp;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;

/**
 * Created by Patryk on 7/27/2015.
 */
@Module
public class TwitterModule {

    @Provides
    TwitterApi providesTwitterAPI(Retrofit retro, LocalDataCache cache){
        return new TwitterApi(retro, cache);
    }
}

package io.patrykpoborca.cleanarchitecture.dagger.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ApplicationScope;
import io.patrykpoborca.cleanarchitecture.network.base.OKHttp;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.util.Constants;
import rx.Scheduler;


@Module
public class NetworkModule {

    @ApplicationScope
    @Provides
    protected OKHttp providesOkHTTP(){
        return new OKHttp();
    }

    @ApplicationScope
    @Provides
    protected Retrofit providesRetrofit(OKHttp okHttp, @Named(Constants.MAIN_THREAD) Scheduler mainThread) {
        return new Retrofit(okHttp, mainThread);
    }
}
package io.patrykpoborca.cleanarchitecture.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ApplicationScope;
import io.patrykpoborca.cleanarchitecture.network.base.OKHttp;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;

/**
 * Created by Patryk on 7/27/2015.
 */
@Module
public class NetworkModule {

    /*
    This is not a linear progression! None of these depend on each other!
    @Provides
    OKHttp providesOkHTTP(){
        return new OKHttp();
    }
    @Provides
    Retrofit providesRetrofit(){
        return new Retrofit();
    }
    */

    @ApplicationScope
    @Provides
    OKHttp providesOkHTTP(){
        return new OKHttp();
    }

    @ApplicationScope
    @Provides
    Retrofit providesRetrofit(OKHttp okHttp){
        return new Retrofit(okHttp);
    }
}
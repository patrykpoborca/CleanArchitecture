package io.patrykpoborca.cleanarchitecture.dagger.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ApplicationScope;
import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;

/**
 * Created by Patryk on 7/27/2015.
 */
@Module
public class LocalModule {

    @ApplicationScope
    @Provides
    LocalDataCache providesDataCache(Application application){
        return new LocalDataCache(application);
    }
}

package io.patrykpoborca.cleanarchitecture.dagger.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ApplicationScope;

/**
 * Created by Patryk on 7/27/2015.
 */
@Module
public class ApplicationModule {

    private static Application sApplication;

    public ApplicationModule(Application application) {
        sApplication = application;
    }

    @Singleton
    @Provides
    Application providesApplication(){
        return sApplication;
    }

}

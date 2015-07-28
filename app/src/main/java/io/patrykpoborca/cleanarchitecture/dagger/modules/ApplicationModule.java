package io.patrykpoborca.cleanarchitecture.dagger.modules;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Patryk on 7/27/2015.
 */
@Module
public class ApplicationModule {

    private static Application sApplication;

    public ApplicationModule(Application application) {
        sApplication = application;
    }

    @Provides
    Application providesApplication(){
        return sApplication;
    }

}

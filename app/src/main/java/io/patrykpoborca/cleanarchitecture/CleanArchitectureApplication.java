package io.patrykpoborca.cleanarchitecture;

import android.app.Application;

import io.patrykpoborca.cleanarchitecture.dagger.components.ApplicationComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.BaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerApplicationComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerBaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerTwitterComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.TwitterComponent;
import io.patrykpoborca.cleanarchitecture.dagger.modules.ApplicationModule;
import io.patrykpoborca.cleanarchitecture.dagger.modules.LocalModule;
import io.patrykpoborca.cleanarchitecture.dagger.modules.NetworkModule;
import io.patrykpoborca.cleanarchitecture.dagger.modules.TwitterModule;

/**
 * Created by Patryk on 7/27/2015.
 */
public class CleanArchitectureApplication extends Application{

    private static BaseComponent sBaseComponent;
    private static TwitterComponent sTwitterAPIComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationComponent applicationComponent = DaggerApplicationComponent
                                        .builder()
                                        .applicationModule(new ApplicationModule(this))
                                        .build();
        sBaseComponent = DaggerBaseComponent.builder()
                .applicationComponent(applicationComponent)
                .build();

        sTwitterAPIComponent = DaggerTwitterComponent.builder()
                                .baseComponent(sBaseComponent)
                                .twitterModule(new TwitterModule())
                                 .build();
    }

    public static TwitterComponent getTwitterAPIComponent(){
        return sTwitterAPIComponent;
    }
    public static BaseComponent getBaseComponent(){
        return sBaseComponent;
    }
}

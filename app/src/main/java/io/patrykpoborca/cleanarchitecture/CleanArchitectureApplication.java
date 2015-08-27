package io.patrykpoborca.cleanarchitecture;

import android.app.Application;

import io.patrykpoborca.cleanarchitecture.dagger.components.ApplicationComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.BaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerApplicationComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerBaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.modules.ApplicationModule;


public class CleanArchitectureApplication extends Application{

    private static BaseComponent sBaseComponent;

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
    }
    public static BaseComponent getBaseComponent(){
        return sBaseComponent;
    }
}

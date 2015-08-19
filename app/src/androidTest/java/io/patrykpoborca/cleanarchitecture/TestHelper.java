package io.patrykpoborca.cleanarchitecture;

import android.app.Application;

import io.patrykpoborca.cleanarchitecture.dagger.DaggerTestClassInjector;
import io.patrykpoborca.cleanarchitecture.dagger.TestClassInjector;
import io.patrykpoborca.cleanarchitecture.dagger.components.ApplicationComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.BaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerApplicationComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerBaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.mockmodules.MockLocalModule;
import io.patrykpoborca.cleanarchitecture.dagger.mockmodules.MockNetworkModule;
import io.patrykpoborca.cleanarchitecture.dagger.modules.ApplicationModule;

public class TestHelper {

    private static ApplicationComponent sApplicationComponent;
    private static BaseComponent sBaseComponent;
    private static TestClassInjector sTestClassInjector;

    public static ApplicationComponent getApplicationComponent(){
        if(sApplicationComponent == null)
        {
            sApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(new Application()))
                    .build();
        }
        return sApplicationComponent;
    }

    public static BaseComponent getBaseComponent(){
        if(sBaseComponent == null){
            sBaseComponent = DaggerBaseComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .localModule(new MockLocalModule())
                    .networkModule(new MockNetworkModule())
                    .build();
        }
        return sBaseComponent;
    }

    public static TestClassInjector getTestClassInjector(){
        if(sTestClassInjector == null){
            sTestClassInjector = DaggerTestClassInjector.builder()
                    .baseComponent(getBaseComponent())
                    .build();
        }

        return sTestClassInjector;
    }
}
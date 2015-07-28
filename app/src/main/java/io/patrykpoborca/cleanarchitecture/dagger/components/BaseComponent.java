package io.patrykpoborca.cleanarchitecture.dagger.components;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.dagger.modules.LocalModule;
import io.patrykpoborca.cleanarchitecture.dagger.modules.NetworkModule;
import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.base.OKHttp;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;

/**
 * Created by Patryk on 7/27/2015.
 */

// AppModule [Application] |AppComponent| <- BaseComponent needs that stuff...
@Component(modules = {LocalModule.class, NetworkModule.class}, dependencies = ApplicationComponent.class)
public interface BaseComponent {

    OKHttp getOkHTTP();

    Retrofit getRetrofit();

    LocalDataCache getLocalDataCache();
}



/*
How Dagger Works

Twitter App

OKHttp -> Retrofit -> TwitterAPI -> MainActivityStupid***
                    LocalDataCache /

        public constructor(OKHttp instance){
            new TwitterAPI(new Retrofit(instance));
        }

        inversion of control: Tl;dr don't instantiate objects in your class that can be passed to it.

        Leads us to Dependency Injection: (Think about the name) Means to have the classes that would otherwise be instantiated/or passed into the constructor
        injected into our class.

        Brings us to this...
        @Inject
        public someConstructor(Object1 o, Object2 o2, ...){
            //Means that those objects can be injected via Dagger
        }

        How does Dagger work?

        Modules (Providers of Data!)
        Have interfaces called Components

        Components permit any dependency to access the models which a provider provides...
        meaning if
        Module1 Provides class Foo, class Bar, but Component1 only exposes Foo

        Then Component2 which has a dependency on Component1, can only get Foo, not Bar.

        Module |->Components(components are the bottleneck) -> public


 */
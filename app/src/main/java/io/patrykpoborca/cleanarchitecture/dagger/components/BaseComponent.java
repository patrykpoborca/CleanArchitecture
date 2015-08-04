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
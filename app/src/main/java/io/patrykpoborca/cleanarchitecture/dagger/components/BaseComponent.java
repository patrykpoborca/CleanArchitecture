package io.patrykpoborca.cleanarchitecture.dagger.components;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.dagger.modules.LocalModule;
import io.patrykpoborca.cleanarchitecture.dagger.modules.NetworkModule;
import io.patrykpoborca.cleanarchitecture.dagger.modules.ThreadingModule;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ApplicationScope;
import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.base.OKHttp;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.util.Constants;
import rx.Scheduler;

/**
 * Created by Patryk on 7/27/2015.
 */

// AppModule [Application] |AppComponent| <- BaseComponent needs that stuff..
@ApplicationScope
@Component(modules = {LocalModule.class, NetworkModule.class, ThreadingModule.class}, dependencies = ApplicationComponent.class)
public interface BaseComponent {

    OKHttp getOkHTTP();

    Retrofit getRetrofit();

    LocalDataCache getLocalDataCache();

    @Named(Constants.MAIN_THREAD)
    Scheduler getMainScheduler();
}
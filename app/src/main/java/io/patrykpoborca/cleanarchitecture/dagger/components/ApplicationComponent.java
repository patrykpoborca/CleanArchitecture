package io.patrykpoborca.cleanarchitecture.dagger.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.dagger.modules.ApplicationModule;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ApplicationScope;

/**
 * Created by Patryk on 7/27/2015.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Application getApplication();

}

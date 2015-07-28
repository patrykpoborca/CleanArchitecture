package io.patrykpoborca.cleanarchitecture.dagger.components;

import android.app.Application;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.dagger.modules.ApplicationModule;

/**
 * Created by Patryk on 7/27/2015.
 */
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Application getApplication();

}

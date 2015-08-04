package io.patrykpoborca.cleanarchitecture.ui.MVPIC.dagger;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.dagger.components.BaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.TwitterComponent;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.MainActivityMVPIC;

@Component(dependencies = {BaseComponent.class, TwitterComponent.class}, modules = ActivityModule.class)
public interface ActivityInjector {
    void inject(MainActivityMVPIC activityMVPIC);
}

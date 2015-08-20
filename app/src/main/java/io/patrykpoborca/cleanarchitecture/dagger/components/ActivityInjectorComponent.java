package io.patrykpoborca.cleanarchitecture.dagger.components;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.dagger.modules.PresenterModule;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;
import io.patrykpoborca.cleanarchitecture.ui.MVP.MainActivityMVP;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.MainActivityMVPCI;
import io.patrykpoborca.cleanarchitecture.ui.MVVM.MainActivityMVVM;
import io.patrykpoborca.cleanarchitecture.ui.MainActivityStupid;

/**
 * Created by Patryk on 7/28/2015.
 */
@ActivityScope
@Component(dependencies = {BaseComponent.class}, modules = PresenterModule.class)
public interface ActivityInjectorComponent {

    void inject(MainActivityStupid activity);

    void inject(MainActivityMVP activityMVP);

    void inject(MainActivityMVVM activityMVVM);

    void inject(MainActivityMVPCI activityMVPCI);
}

package io.patrykpoborca.cleanarchitecture.dagger.components;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.dagger.modules.PresenterModule;
import io.patrykpoborca.cleanarchitecture.ui.MVP.MainActivityMVP;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.MainActivityMVPIC;
import io.patrykpoborca.cleanarchitecture.ui.MVVM.MainActivityMVVM;
import io.patrykpoborca.cleanarchitecture.ui.MainActivityStupid;

/**
 * Created by Patryk on 7/28/2015.
 */
@Component(dependencies = {TwitterComponent.class, BaseComponent.class}, modules = PresenterModule.class)
public interface ActivityInjectorComponent {

    void inject(MainActivityStupid activity);

    void inject(MainActivityMVP activityMVP);

    void inject(MainActivityMVVM activityMVVM);
}

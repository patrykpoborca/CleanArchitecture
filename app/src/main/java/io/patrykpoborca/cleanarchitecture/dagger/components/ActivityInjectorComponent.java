package io.patrykpoborca.cleanarchitecture.dagger.components;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.dagger.modules.PresenterModule;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;
import io.patrykpoborca.cleanarchitecture.ui.MVP.TweeterActivityMVP;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.TweeterActivityMVPCI;
import io.patrykpoborca.cleanarchitecture.ui.MVVM.TweeterActivityMVVM;
import io.patrykpoborca.cleanarchitecture.ui.PlainTweeterActivity;

/**
 * Created by Patryk on 7/28/2015.
 */
@ActivityScope
@Component(dependencies = {BaseComponent.class}, modules = PresenterModule.class)
public interface ActivityInjectorComponent {

    void inject(PlainTweeterActivity activity);

    void inject(TweeterActivityMVP activityMVP);

    void inject(TweeterActivityMVVM activityMVVM);

    void inject(TweeterActivityMVPCI activityMVPCI);
}

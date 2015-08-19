package io.patrykpoborca.cleanarchitecture.dagger;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.tests.ViewModelTest;
import io.patrykpoborca.cleanarchitecture.dagger.components.BaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;

@ActivityScope
@Component(dependencies = BaseComponent.class)
public interface TestClassInjector {
    void inject(ViewModelTest test);
}

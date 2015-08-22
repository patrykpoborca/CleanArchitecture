package io.patrykpoborca.cleanarchitecture.dagger;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.dagger.mockmodules.MockTestModule;
import io.patrykpoborca.cleanarchitecture.tests.MVPCITest;
import io.patrykpoborca.cleanarchitecture.tests.PresenterTestMvp;
import io.patrykpoborca.cleanarchitecture.tests.ViewModelTest;
import io.patrykpoborca.cleanarchitecture.dagger.components.BaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;

@ActivityScope
@Component(dependencies = BaseComponent.class, modules = MockTestModule.class)
public interface TestClassInjector {
    void inject(ViewModelTest test);

    void inject(PresenterTestMvp presenterTestMvp);

    void inject(MVPCITest mvpciTest);
}

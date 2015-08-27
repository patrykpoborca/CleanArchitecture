package io.patrykpoborca.cleanarchitecture.dagger;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.dagger.mockmodules.MockTestModule;
import io.patrykpoborca.cleanarchitecture.tests.MVPCITest;
import io.patrykpoborca.cleanarchitecture.tests.MVPTest;
import io.patrykpoborca.cleanarchitecture.tests.MVVMTest;
import io.patrykpoborca.cleanarchitecture.dagger.components.BaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;

@ActivityScope
@Component(dependencies = BaseComponent.class, modules = MockTestModule.class)
public interface TestClassInjector {
    void inject(MVVMTest test);

    void inject(MVPTest MVPTest);

    void inject(MVPCITest mvpciTest);

}

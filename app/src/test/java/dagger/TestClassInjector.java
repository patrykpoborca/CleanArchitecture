package dagger;

import dagger.mockmodules.MockTestModule;
import io.patrykpoborca.cleanarchitecture.dagger.components.BaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;
import tests.MVPCITest;
import tests.MVVMTest;
import tests.PresenterTestMvp;

@ActivityScope
@Component(dependencies = BaseComponent.class, modules = MockTestModule.class)
public interface TestClassInjector {
    void inject(MVVMTest viewmodelTest);

    void inject(MVPCITest presenterTest);

    void inject(PresenterTestMvp presenterTestMvp);
}

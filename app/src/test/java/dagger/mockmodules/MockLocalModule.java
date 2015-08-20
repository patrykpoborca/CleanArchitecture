package dagger.mockmodules;

import android.app.Application;

import io.patrykpoborca.cleanarchitecture.dagger.modules.LocalModule;
import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import mockimpl.MockLocalDataCache;

public class MockLocalModule extends LocalModule{

    @Override
    protected LocalDataCache providesDataCache(Application application) {
        return new MockLocalDataCache(application);
    }
}

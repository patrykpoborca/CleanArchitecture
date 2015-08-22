package dagger.mockmodules;

import io.patrykpoborca.cleanarchitecture.dagger.modules.ThreadingModule;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class MockThreadingModule extends ThreadingModule {

    @Override
    public Scheduler providesMainThread() {
        return Schedulers.immediate();
    }
}

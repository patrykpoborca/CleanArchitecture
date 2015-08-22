package io.patrykpoborca.cleanarchitecture.dagger.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.util.Constants;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

@Module
public class ThreadingModule {
    @Named(Constants.MAIN_THREAD)
    @Provides
    public Scheduler providesMainThread(){
        return AndroidSchedulers.mainThread();
    }
}

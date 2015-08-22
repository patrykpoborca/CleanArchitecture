package io.patrykpoborca.cleanarchitecture.dagger.mockmodules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;
import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockMVPCIPview;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockMainActivityPview;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockTweeterApi;
import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVP.MainMVPPresenterImpl;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPresenter;
import io.patrykpoborca.cleanarchitecture.util.Constants;
import rx.Scheduler;

@Module
public class MockTestModule {

    @Provides
    @ActivityScope
    MainMVPPresenter providesMainPresenter(TweeterApi api, Retrofit retrofit) {
        return new MainMVPPresenterImpl(api, retrofit);
    }

    @ActivityScope
    @Provides
    public MockMainActivityPview providesMockMainPview(MainMVPPresenter presenter) {
        MockMainActivityPview mockMainActivityPview = new MockMainActivityPview();
        presenter.registerView(mockMainActivityPview);
        return mockMainActivityPview;
    }

    @ActivityScope
    @Provides
    public TweeterApi providesMockTweeter(Retrofit retrofit, LocalDataCache dataCache, @Named(Constants.MAIN_THREAD) Scheduler mainThread) {
        return new MockTweeterApi(retrofit, dataCache, mainThread);
    }

    @ActivityScope
    @Provides
    public MockMVPCIPview providesMockMVPCCIView(){
        return new MockMVPCIPview();
    }
}

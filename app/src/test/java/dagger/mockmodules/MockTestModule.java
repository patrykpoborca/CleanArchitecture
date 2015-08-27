package dagger.mockmodules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;
import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVP.TweeterMVPPresenterImpl;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.TweeterMVPPresenter;
import io.patrykpoborca.cleanarchitecture.util.Constants;
import mockimpl.MockMVPCIPview;
import mockimpl.MockTweeterActivityPview;
import mockimpl.MockTweeterApi;
import rx.Scheduler;

@Module
public class MockTestModule {

    @Provides
    @ActivityScope
    TweeterMVPPresenter providesMainPresenter(TweeterApi api, Retrofit retrofit){
        return new TweeterMVPPresenterImpl(api, retrofit);
    }

    @ActivityScope
    @Provides
    public MockTweeterActivityPview providesMockMainPview(TweeterMVPPresenter presenter){
        MockTweeterActivityPview mockTweeterActivityPview = new MockTweeterActivityPview();
        presenter.registerView(mockTweeterActivityPview);
        return mockTweeterActivityPview;
    }

    @ActivityScope
    @Provides
    public TweeterApi providesMockTweeter(Retrofit retrofit, LocalDataCache dataCache, @Named(Constants.MAIN_THREAD) Scheduler mainScheduler){
        return new MockTweeterApi(retrofit, dataCache, mainScheduler);
    }

    @ActivityScope
    @Provides
    public MockMVPCIPview providesMockMVPCCIView(){
        return new MockMVPCIPview();
    }
}

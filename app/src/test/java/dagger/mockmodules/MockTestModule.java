package dagger.mockmodules;

import dagger.Module;
import dagger.Provides;
import io.patrykpoborca.cleanarchitecture.dagger.scopes.ActivityScope;
import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.OKHttp;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVP.MainMVPPresenterImpl;
import io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces.MainMVPPresenter;
import mockimpl.MockMainActivityPview;
import mockimpl.MockTweeterApi;

@Module
public class MockTestModule {

    @Provides
    @ActivityScope
    MainMVPPresenter providesMainPresenter(TweeterApi api, Retrofit retrofit){
        return new MainMVPPresenterImpl(api, retrofit);
    }

    @ActivityScope
    @Provides
    public MockMainActivityPview providesMockMainPview(MainMVPPresenter presenter){
        MockMainActivityPview mockMainActivityPview = new MockMainActivityPview();
        presenter.registerView(mockMainActivityPview);
        return mockMainActivityPview;
    }

    @ActivityScope
    @Provides
    public TweeterApi providesMockTweeter(Retrofit retrofit, LocalDataCache dataCache){
        return new MockTweeterApi(retrofit, dataCache);
    }
}

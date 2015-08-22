package dagger.mockmodules;

import io.patrykpoborca.cleanarchitecture.dagger.modules.NetworkModule;
import io.patrykpoborca.cleanarchitecture.network.base.OKHttp;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import mockimpl.MockOkHTTP;
import mockimpl.MockRetrofit;
import rx.Scheduler;

public class MockNetworkModule extends NetworkModule {

    @Override
    protected OKHttp providesOkHTTP() {
        return new MockOkHTTP();
    }

    @Override
    protected Retrofit providesRetrofit(OKHttp okHttp, Scheduler mainScheduler) {
        return new MockRetrofit(okHttp, mainScheduler);
    }
}

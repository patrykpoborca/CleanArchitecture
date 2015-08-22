package io.patrykpoborca.cleanarchitecture.dagger.mockmodules;

import javax.inject.Named;

import io.patrykpoborca.cleanarchitecture.dagger.modules.NetworkModule;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockOkHTTP;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockRetrofit;
import io.patrykpoborca.cleanarchitecture.network.base.OKHttp;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.util.Constants;
import rx.Scheduler;

public class MockNetworkModule extends NetworkModule {

    @Override
    protected OKHttp providesOkHTTP() {
        return new MockOkHTTP();
    }

    @Override
    protected Retrofit providesRetrofit(OKHttp okHttp, @Named(Constants.MAIN_THREAD)Scheduler mainThread) {
        return new MockRetrofit(okHttp, mainThread);
    }
}

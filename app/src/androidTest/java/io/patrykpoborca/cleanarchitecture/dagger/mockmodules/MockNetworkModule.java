package io.patrykpoborca.cleanarchitecture.dagger.mockmodules;

import io.patrykpoborca.cleanarchitecture.dagger.modules.NetworkModule;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockOkHTTP;
import io.patrykpoborca.cleanarchitecture.mockimpl.MockRetrofit;
import io.patrykpoborca.cleanarchitecture.network.base.OKHttp;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;

public class MockNetworkModule extends NetworkModule {

    @Override
    protected OKHttp providesOkHTTP() {
        return new MockOkHTTP();
    }

    @Override
    protected Retrofit providesRetrofit(OKHttp okHttp) {
        return new MockRetrofit(okHttp);
    }
}

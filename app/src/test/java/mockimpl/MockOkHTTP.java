package mockimpl;

import io.patrykpoborca.cleanarchitecture.network.base.OKHttp;

public class MockOkHTTP extends OKHttp {
    @Override
    public String rawResponse() {
        return "Mocked raw response: ";
    }
}

package mockimpl;

import android.util.Log;

import io.patrykpoborca.cleanarchitecture.localdata.LocalDataCache;
import io.patrykpoborca.cleanarchitecture.network.TweeterApi;
import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.models.UserProfile;
import rx.Observable;

public class MockTweeterApi extends TweeterApi{
    public MockTweeterApi(Retrofit retrofit, LocalDataCache dataCache) {
        super(retrofit, dataCache);
    }

    @Override
    public Observable<UserProfile> login(String username, String password) {
        return Observable.just(new UserProfile(username, password));
    }

    @Override
    public Observable<Object> logout() {
        return Observable.just(null);
    }
}

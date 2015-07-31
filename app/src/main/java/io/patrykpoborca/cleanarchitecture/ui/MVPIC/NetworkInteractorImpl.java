package io.patrykpoborca.cleanarchitecture.ui.MVPIC;

import javax.inject.Inject;

import io.patrykpoborca.cleanarchitecture.network.base.Retrofit;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces.NetworkInteractor;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import rx.Observable;

/**
 * Created by Patryk on 7/29/2015.
 */
public class NetworkInteractorImpl implements NetworkInteractor {


    private final Retrofit retrofit;

    @Inject
    public NetworkInteractorImpl(Retrofit retrofit){
        this.retrofit = retrofit;
    }


    @Override
    public Observable<UserProfile> attemptLogin(String username, String password) {
        return retrofit.performRequest(username, password);
    }
}

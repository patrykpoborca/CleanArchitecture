package io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces;

import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import rx.Observable;

/**
 * Created by Patryk on 7/29/2015.
 */
public interface NetworkInteractor {

    public Observable<UserProfile> attemptLogin(String username, String password);
}

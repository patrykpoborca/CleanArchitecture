package io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces;

import java.util.List;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.PView;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import rx.Observable;

/**
 * Created by Patryk on 7/29/2015.
 */
public interface MainActivityMVPICPview extends PView{
    public void loggedIn(UserProfile profile);

    public void loggedOut();
}

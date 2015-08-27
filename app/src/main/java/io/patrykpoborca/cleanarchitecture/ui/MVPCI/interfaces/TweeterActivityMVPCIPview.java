package io.patrykpoborca.cleanarchitecture.ui.MVPCI.interfaces;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.PView;
import io.patrykpoborca.cleanarchitecture.ui.MVPCI.models.UserProfile;

/**
 * Created by Patryk on 7/29/2015.
 */
public interface TweeterActivityMVPCIPview extends PView{
    public void loggedIn(UserProfile profile);

    public void loggedOut();
}

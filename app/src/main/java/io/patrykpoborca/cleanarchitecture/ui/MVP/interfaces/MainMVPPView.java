package io.patrykpoborca.cleanarchitecture.ui.MVP.interfaces;

import java.util.List;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.PView;
import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;

/**
 * Created by Patryk on 7/28/2015.
 */
public interface MainMVPPView extends PView {
    public void displayFetchedTweet(String tweet);

    public void displayPreviousTweets(List<String> list);

    public void setUserButtonText(String text);

    public void toggleProgressbar(boolean show);

    void toggleLoginContainer(boolean b);
}

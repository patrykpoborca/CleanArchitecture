package io.patrykpoborca.cleanarchitecture.ui.MVPIC.interfaces;

import java.util.List;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.PView;
import rx.Observable;

/**
 * Created by Patryk on 7/29/2015.
 */
public interface MainActivityMVPICPview extends PView{

    public void weHaveExceeded3TweetsOnBackend();

}

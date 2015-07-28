package io.patrykpoborca.cleanarchitecture.ui.MVPIC.base;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.PView;
import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.Presenter;

/**
 * Created by Patryk on 7/28/2015.
 */
public interface MVPICPresenter extends Presenter<MainActivityP{

    public void registerView(T activity);
    public void onAttach();
    public void onDetach();
}
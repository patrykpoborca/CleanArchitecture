package io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces;

/**
 * Created by Patryk on 7/28/2015.
 */
public interface Presenter<T extends PView> {

    public void registerView(T activity);
    public void onAttach();
    public void onDetach();
}

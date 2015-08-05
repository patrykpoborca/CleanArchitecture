package io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces;

/**
 * Created by Patryk on 7/28/2015.
 */
public interface PView {

    public void toggleProgressBar(boolean loading);

    public void displayToast(String toast);
}

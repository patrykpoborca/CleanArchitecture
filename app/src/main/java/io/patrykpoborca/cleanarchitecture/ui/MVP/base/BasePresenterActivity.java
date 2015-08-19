package io.patrykpoborca.cleanarchitecture.ui.MVP.base;

import android.os.Bundle;

import io.patrykpoborca.cleanarchitecture.ui.BaseCleanArchitectureActivity;
import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.PView;
import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.Presenter;

/**
 * Created by Patryk on 7/28/2015.
 */
public abstract class BasePresenterActivity<T extends Presenter> extends BaseCleanArchitectureActivity implements PView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().registerView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onAttach();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().onDetach();
    }

    protected abstract void registerViewToPresenter();
    protected abstract T getPresenter();
}

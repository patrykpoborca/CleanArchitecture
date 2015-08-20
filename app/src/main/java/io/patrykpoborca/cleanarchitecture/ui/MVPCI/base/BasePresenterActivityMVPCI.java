package io.patrykpoborca.cleanarchitecture.ui.MVPCI.base;

import android.os.Bundle;

import io.patrykpoborca.cleanarchitecture.ui.BaseCleanArchitectureActivity;
import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.PView;

public abstract class BasePresenterActivityMVPCI<T extends BasePresenterMVPCI> extends BaseCleanArchitectureActivity implements PView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().registerPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onAttach();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().onDettach();
    }

    protected abstract T getPresenter();
}

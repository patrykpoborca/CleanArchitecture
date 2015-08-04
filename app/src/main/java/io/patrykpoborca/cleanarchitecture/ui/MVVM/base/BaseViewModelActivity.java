package io.patrykpoborca.cleanarchitecture.ui.MVVM.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.patrykpoborca.cleanarchitecture.ui.BaseCAActivity;

public abstract class BaseViewModelActivity<T extends BaseViewModel> extends BaseCAActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewModel().onAttach();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getViewModel().onDettach();
    }

    protected abstract T getViewModel();
}

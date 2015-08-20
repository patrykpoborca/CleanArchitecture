package io.patrykpoborca.cleanarchitecture.ui.MVPCI.base;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.PView;

public class BasePresenterMVPCI<T extends PView> {

    private T pView;

    public void onAttach(){

    }
    public void onDettach(){

    }

    public void registerPresenter(T view){
        this.pView = view;
    }

    protected T getPView(){
        return this.pView;
    }
}

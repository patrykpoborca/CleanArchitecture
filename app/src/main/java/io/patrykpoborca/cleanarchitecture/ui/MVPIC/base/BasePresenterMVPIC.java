package io.patrykpoborca.cleanarchitecture.ui.MVPIC.base;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.PView;

public class BasePresenterMVPIC<T extends PView> {

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

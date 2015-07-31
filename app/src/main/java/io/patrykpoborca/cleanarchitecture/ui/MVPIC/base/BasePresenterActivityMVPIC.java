package io.patrykpoborca.cleanarchitecture.ui.MVPIC.base;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.patrykpoborca.cleanarchitecture.ui.MVP.base.BasePresenterActivity;
import io.patrykpoborca.cleanarchitecture.ui.MVP.base.Interfaces.Presenter;
import rx.Subscription;

/**
 * Created by Patryk on 7/31/2015.
 */
public abstract class BasePresenterActivityMVPIC<T extends Presenter> extends BasePresenterActivity<T> {

    List<Subscription> subscriptionList;

    protected void registerSubscription(Subscription subscription){
        if(subscriptionList == null){
            subscriptionList = new ArrayList<>();
        }
        this.subscriptionList.add(subscription);
    }

    @Override
    protected void onStop() {
        super.onStop();
        for(int i=0; i < this.subscriptionList.size(); i++){
            if(!this.subscriptionList.get(i).isUnsubscribed()){
                subscriptionList.get(i).unsubscribe();
            }
        }
    }
}

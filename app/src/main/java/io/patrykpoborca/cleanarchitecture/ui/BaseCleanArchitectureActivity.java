package io.patrykpoborca.cleanarchitecture.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

public class BaseCleanArchitectureActivity extends AppCompatActivity{

    private List<Subscription> subscriptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(subscriptions == null){
            subscriptions = new ArrayList<>();
        }
    }

    protected void registerSubscription(Subscription subscription){
        subscriptions.add(subscription);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unsubscribeSubscriptions();
    }

    protected void unsubscribeSubscriptions(){
        for(int i= 0; i < subscriptions.size(); i++){
            subscriptions.get(i).unsubscribe();
        }
    }
}

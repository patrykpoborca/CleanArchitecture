package io.patrykpoborca.cleanarchitecture.dagger.interactors;

import android.content.Context;

import io.patrykpoborca.cleanarchitecture.dagger.interactors.base.BaseInteractor;

/**
 * Created by Patryk on 7/27/2015.
 */
public class StringResourceInteractor extends BaseInteractor{

    private final Context sContext;
    public StringResourceInteractor(Context context) {
        sContext = context;
    }

    public String getString(int id) {
        return sContext.getResources().getString(id);
    }
}

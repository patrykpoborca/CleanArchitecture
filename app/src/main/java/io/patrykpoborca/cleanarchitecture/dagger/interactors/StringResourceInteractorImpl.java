package io.patrykpoborca.cleanarchitecture.dagger.interactors;

import android.content.Context;

import io.patrykpoborca.cleanarchitecture.dagger.interactors.interfaces.StringResourceInteractor;

/**
 * Created by Patryk on 7/27/2015.
 */
public class StringResourceInteractorImpl implements StringResourceInteractor{

    private final Context sContext;
    public StringResourceInteractorImpl(Context context) {
        sContext = context;
    }

    @Override
    public String getString(int id) {
        return sContext.getResources().getString(id);
    }
}

package io.patrykpoborca.cleanarchitecture.dagger.components;

import dagger.Component;
import io.patrykpoborca.cleanarchitecture.dagger.modules.TwitterModule;
import io.patrykpoborca.cleanarchitecture.network.TwitterApi;

/**
 * Created by Patryk on 7/27/2015.
 */
@Component(modules = {TwitterModule.class}, dependencies = {BaseComponent.class})
public interface TwitterComponent {

    //So now... App <- Base <- TwitterAPI
    TwitterApi getTwitterAPI();
}

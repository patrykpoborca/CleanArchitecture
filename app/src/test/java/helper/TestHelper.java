package helper;

import android.app.Application;

import dagger.DaggerTestClassInjector;
import dagger.TestClassInjector;
import dagger.mockmodules.MockLocalModule;
import dagger.mockmodules.MockNetworkModule;
import dagger.mockmodules.MockThreadingModule;
import io.patrykpoborca.cleanarchitecture.dagger.components.ApplicationComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.BaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerApplicationComponent;
import io.patrykpoborca.cleanarchitecture.dagger.components.DaggerBaseComponent;
import io.patrykpoborca.cleanarchitecture.dagger.modules.ApplicationModule;


public class TestHelper {

    private static ApplicationComponent sApplicationComponent;
    private static BaseComponent sBaseComponent;
    private static TestClassInjector sTestClassInjector;

    public static ApplicationComponent getApplicationComponent(){
        if(sApplicationComponent == null)
        {
            sApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(new Application()))
                    .build();
        }
        return sApplicationComponent;
    }

    public static BaseComponent getBaseComponent(){
        if(sBaseComponent == null){
            sBaseComponent = DaggerBaseComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .localModule(new MockLocalModule())
                    .networkModule(new MockNetworkModule())
                    .threadingModule(new MockThreadingModule())
                    .build();
        }
        return sBaseComponent;
    }

    public static TestClassInjector getTestClassInjector(){
        if(sTestClassInjector == null){
            sTestClassInjector = DaggerTestClassInjector.builder()
                    .baseComponent(getBaseComponent())
                    .build();
        }

        return sTestClassInjector;
    }

    public static void waitFor(IWaitingCallback callback){
        waitFor(10, callback);
    }

    public static void waitFor(int maxCycles, IWaitingCallback callback){
        while(true){
            maxCycles --;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(callback.checkCondition()){
                break;
            }
            if(maxCycles <= 0){
                break;
            }
        }
    }

    public static interface IWaitingCallback{
        /**
         *
         * @return true if condition is met, false if we should keep waiting
         */
        public boolean checkCondition();
    }
}
package io.patrykpoborca.cleanarchitecture.network.base;

import java.util.concurrent.TimeUnit;

import io.patrykpoborca.cleanarchitecture.ui.MVPIC.models.UserProfile;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Patryk on 7/27/2015.
 */
public class Retrofit {

    protected OKHttp okHttp;
    public Retrofit(OKHttp okHttp) {
        this.okHttp = okHttp;
    }

    public Observable<String> completeRequest(){
        return Observable.just(okHttp.rawResponse() + " Some Parsing Done")
                .delay(2, TimeUnit.SECONDS);
    }

    public Observable<String> fetchSomePage(String url){
        return Observable.just("<h2>" + "Fake response from fake retrofit: " + url + " </h2>")
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }
}

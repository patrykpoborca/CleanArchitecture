package io.patrykpoborca.cleanarchitecture.network.base;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

public class Retrofit {

    protected OKHttp okHttp;
    protected final Scheduler mainScheduler;
    public Retrofit(OKHttp okHttp, Scheduler mainScheduler) {
        this.okHttp = okHttp;
        this.mainScheduler = mainScheduler;
    }

    public Observable<String> completeRequest(){
        return Observable.just(okHttp.rawResponse() + " Some Parsing Done")
                .delay(2, TimeUnit.SECONDS);
    }

    public Observable<String> fetchSomePage(String url){
        return Observable.just("<h2>" + "Fake response from fake retrofit: " + url + " </h2>")
                .delay(2, TimeUnit.SECONDS)
                .observeOn(mainScheduler);
    }
}

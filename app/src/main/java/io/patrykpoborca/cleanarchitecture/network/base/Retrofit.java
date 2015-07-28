package io.patrykpoborca.cleanarchitecture.network.base;

/**
 * Created by Patryk on 7/27/2015.
 */
public class Retrofit {

    OKHttp okHttp;
    public Retrofit(OKHttp okHttp) {
        this.okHttp = okHttp;
    }

    public String parseString(){
        return okHttp.rawResponse() + " Some Parsing Done";
    }
}

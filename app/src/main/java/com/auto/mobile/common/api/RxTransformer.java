package com.auto.mobile.common.api;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wudonglin on 2017/10/10.
 */

public class RxTransformer {

    public static <T>Observable.Transformer<T,T> defaultShedulers(){
        return new Observable.Transformer<T,T>(){

            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        ;
            }
        };
    }
}

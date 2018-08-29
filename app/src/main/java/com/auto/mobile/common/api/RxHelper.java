package com.auto.mobile.common.api;



import com.auto.mobile.common.result.Response;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 处理返回的数据
 */
public class RxHelper {
    /**
     * 用来统一处理Http的resultCode,并将返回的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    public static <T> Observable.Transformer<Response<T>, T> handleResult() {
        return new Observable.Transformer<Response<T>, T>() {
            @Override
            public Observable<T> call(Observable<Response<T>> tObservable) {
                return tObservable.flatMap(new Func1<Response<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(Response<T> result) {
                        if (result.getCode()==1 && result.getData() != null) {
                            //当code 返回1的时候 是成功的
                            return createData(result.getData());
                        } else {
                            //否则返回错误信息
                            return Observable.error(new Exception(result.getMsg()));
                        }
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /**
     * 将数据存入subscriber
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }
}

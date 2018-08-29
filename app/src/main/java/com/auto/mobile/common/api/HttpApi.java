package com.auto.mobile.common.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具类
 * Created by wudonglin on 2017/10/10.
 */

public class HttpApi {

    private Retrofit retrofit;

    //请求超时时间
    private static final int DEFAULT_TIMEOUT=10;
    private static HttpApi instance;

    private HttpApi(){
        OkHttpClient.Builder httpClientBuilder=new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit=new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ApiInterface.BASE_URL)
                .build();
    }

    /**
     * 单利模式
     * @return
     */
    public static HttpApi getInstance(){
        if (instance==null){
            synchronized (HttpApi.class){
                if (instance==null){
                    instance=new HttpApi();
                }
            }
        }
        return instance;
    }

    //返回一个泛型类
    public <T> T getService(Class<T> service){
        return retrofit.create(service);
    }

}

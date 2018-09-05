package com.auto.mobile.common.application;

import android.app.Application;
import android.content.Context;

/**
 * 程序入口
 */
public class MyApplication extends Application {

    public static String AccessKey = "UGblyQDw1VUetFOuUmF_57mO80WDJwupejde6Psa";//七牛AK

    public static String SecretKey = "zj4Z1s2IBCJtKyvJeuMVRx_DsS5ZbzM8oAZ0MtOj";//七牛SK

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
}

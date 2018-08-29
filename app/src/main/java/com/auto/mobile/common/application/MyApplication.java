package com.auto.mobile.common.application;

import android.app.Application;
import android.content.Context;

/**
 * 程序入口
 */
public class MyApplication extends Application {

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

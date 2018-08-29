package com.auto.mobile.common.api;

import android.app.ProgressDialog;
import android.content.Context;


import com.auto.mobile.common.util.CommonUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 网络请求操作
 * Created by wudonglin on 2017/10/10.
 */

public abstract class RxSubscribe<T> extends Subscriber<T> {

    private Context mContext;
    private ProgressDialog dialog;
    //是否显示对话框
    private boolean mShowDialog;

    public RxSubscribe(Context context,boolean showDialog){
        this.mContext=context;
        this.mShowDialog=showDialog;
    }

    @Override
    public void onCompleted() {
        if (!isUnsubscribed()){
            unsubscribe();
        }
        if (dialog!=null&&mShowDialog==true){
            dialog.dismiss();
        }
        dialog=null;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!CommonUtil.isNetWorkConnected(mContext)){
            if (!isUnsubscribed()){
                unsubscribe();
            }
            _onError("网络不可用");
        }else {
            if (dialog==null&&mShowDialog==true){
                dialog=new ProgressDialog(mContext);
                dialog.setMessage("正在加载中...");
                dialog.show();
            }
        }

    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!CommonUtil.isNetWorkConnected(mContext)){
            _onError("网络不可用");
        }else if (e instanceof SocketTimeoutException||e instanceof ConnectException){
            _onError("请求服务器异常，请稍后再试...");
        }else if (e instanceof HttpException){
            _onError("服务器异常，请稍后再试");
        }else {
            _onError(e.getMessage());
        }
        if (dialog!=null&&mShowDialog==true)
            dialog.dismiss();
        dialog=null;
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}

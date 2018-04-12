package com.freak.legalaid.library.rxjava;

import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;

public class SubscriberCallBack extends Subscriber {
    private ApiCallback apiCallback;

    public SubscriberCallBack(ApiCallback apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onCompleted() {

    }

    /**
     * 错误是调用
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        try {
            String msg;
            if (e instanceof SocketTimeoutException) {
                msg = "连接服务器超时,请检查您的网络状态";
                apiCallback.onFailure(msg);
            } else if (e instanceof ConnectException) {
                msg = "网络中断，请检查您的网络状态";
                apiCallback.onFailure(msg);
            }else if(e instanceof TimeoutException){
                msg = "连接超时，请检查您的网络状态";
                apiCallback.onFailure(msg);
            }else {
                Log.e("json","发送错误",e);
                e.printStackTrace();
                apiCallback.onFailure(e.getMessage());
            }
        }catch (Exception e1){
            apiCallback.onFailure(e1.getMessage());
            e1.printStackTrace();
        }
    }

    /**
     * 成功是调用
     * @param o
     */
    @Override
    public void onNext(Object o) {
        apiCallback.onSuccess(o);
    }
}

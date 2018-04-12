package com.freak.legalaid.library.rxjava;

public interface ApiCallback<T> {
    void onSuccess(T model);

    void onFailure(String msg);
}

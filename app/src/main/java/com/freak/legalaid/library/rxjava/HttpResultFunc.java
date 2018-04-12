package com.freak.legalaid.library.rxjava;

import rx.functions.Func1;

public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
    @Override
    public T call(HttpResult<T> tHttpResult) {
        if (tHttpResult.getCode() != 0) {
            throw new ApiException(tHttpResult.getErrorMsg());
        }
        return tHttpResult.getData();
    }
}

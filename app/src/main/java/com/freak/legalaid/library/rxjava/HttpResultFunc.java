package com.freak.legalaid.library.rxjava;

import rx.functions.Func1;

/**
 * 此方法是接口返回数据的解析
 * @param <T>
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
    @Override
    public T call(HttpResult<T> tHttpResult) {
        if (tHttpResult.getError_code() != 0) {
            throw new ApiException(tHttpResult.getReason());
        }
        return tHttpResult.getResult();
    }
}

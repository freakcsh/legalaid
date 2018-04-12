package com.freak.legalaid.library.rxjava;

public class HttpResult<T> {
    private int code;
    private T data;
    private String errorMsg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (null != data) {
            sb.append(data.toString());
        }
        return sb.toString();
    }
}

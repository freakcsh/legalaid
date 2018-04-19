package com.freak.legalaid.library.rxjava;

/**
 * 此方法是根据接口返回的数据去定义的，抽取出返回json数据的对象进行去解析
 * @param <T> result是接口数据的一个对象，bean类中的数据书写也是书写这个json数据的对象的字段即可
 */
public class HttpResult<T> {
   private String reason;
   private T result;
   private int error_code;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (null != result) {
            sb.append(result.toString());
        }
        return sb.toString();
    }
}

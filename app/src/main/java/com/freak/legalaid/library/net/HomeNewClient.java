package com.freak.legalaid.library.net;

import com.freak.legalaid.app.Constants;
import com.freak.legalaid.bean.HomeDataBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class HomeNewClient {
    private HomeNewCallBack mhHomeNewCallBack;
    private static HomeNewClient mHomeNewClient;
    private Gson mGson;

    private HomeNewClient() {
        mGson = new Gson();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static HomeNewClient getInstance() {
        if (mHomeNewClient == null) {
            synchronized (HomeNewClient.class) {
                if (mHomeNewClient == null) {
                    mHomeNewClient = new HomeNewClient();
                }
            }
        }
        return mHomeNewClient;
    }

    /**
     * 根据相应的新闻类型获取新闻数据
     *
     * @param type     新闻的类型
     * @param callBack 新闻的回调接口
     */
    public void getNewsData(String type, HomeNewCallBack callBack) {
        mhHomeNewCallBack = callBack;
        OkHttpUtils.post()
                .url(Constants.NEWS_DATA_URL)
                .addParams("type", type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        mhHomeNewCallBack.onError(e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        HomeDataBean newsDataBean = mGson.fromJson(response, HomeDataBean.class);
                        mhHomeNewCallBack.onSuccess(newsDataBean, id);
                    }
                });
    }

}

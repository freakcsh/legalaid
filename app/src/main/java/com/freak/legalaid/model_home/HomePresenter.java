package com.freak.legalaid.model_home;

import com.freak.legalaid.app.ApiServer;
import com.freak.legalaid.bean.HomeDataBean;
import com.freak.legalaid.library.rxjava.ApiCallback;
import com.freak.legalaid.library.rxjava.HttpMethods;
import com.freak.legalaid.library.rxjava.HttpResultFunc;
import com.freak.legalaid.library.rxjava.RxPresenter;
import com.freak.legalaid.library.rxjava.SubscriberCallBack;

import rx.Observable;

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {
    private ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    /**
     * HomeContract中接口Presenter中的方法，在此方法中进行网络请求
     * @param type 类型
     * @param key appId
     *            SubscriberCallBack（）这个方法是订阅，订阅之后会执行里面的方法
     *            apiServer.getNews(type, key) 得到的是在apiServer里面定义的返回值类型
     *            map（）利用map操作符把我们得到的返回值类型转化成我需要的那部分数据
     */
    @Override
    public void getNews(String type, String key) {
        Observable observable = apiServer.getNews(type, key).map(new HttpResultFunc<HomeDataBean>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<HomeDataBean>() {


            @Override
            public void onSuccess(HomeDataBean model) {
                mView.getNewsSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
            mView.showError(msg);
            }
        }));
    }
}

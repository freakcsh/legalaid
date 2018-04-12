package com.freak.legalaid.model_home;

import com.freak.legalaid.app.ApiServer;
import com.freak.legalaid.bean.HomeDataBean;
import com.freak.legalaid.library.rxjava.ApiCallback;
import com.freak.legalaid.library.rxjava.HttpMethods;
import com.freak.legalaid.library.rxjava.HttpResultFunc;
import com.freak.legalaid.library.rxjava.RxPresenter;
import com.freak.legalaid.library.rxjava.SubscriberCallBack;

import rx.Observable;

/*
   private ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    /**
     * HomeContract中接口Presenter中的方法，在此方法中进行网络请求
     * @param type 类型
     * @param key appId
     */
/*
        Observable observable=apiServer.getNews(type,key).map(new HttpResultFunc<HomeDataBean>());
        addSubscription(observable,new SubscriberCallBack(new ApiCallback<HomeDataBean>() {


            @Override
            public void onSuccess(HomeDataBean model) {

            }

            @Override
            public void onFailure(String msg) {

            }
        }));
 */
public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {
    private ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    /**
     * HomeContract中接口Presenter中的方法，在此方法中进行网络请求
     * @param type 类型
     * @param key appId
     */
    @Override
    public void getNews(String type, String key) {
        Observable observable = apiServer.getNews(type, key).map(new HttpResultFunc<HomeDataBean>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<HomeDataBean>() {


            @Override
            public void onSuccess(HomeDataBean model) {
//                Log.e("freak",model.getResult().getData().toString());
                mView.getNewsSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
            mView.showError(msg);
            }
        }));
    }
}

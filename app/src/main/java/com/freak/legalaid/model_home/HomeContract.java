package com.freak.legalaid.model_home;

import com.freak.legalaid.bean.HomeDataBean;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.freak.legalaid.library.rxjava.BaseView;

public interface HomeContract {
    /**
     * 定义接口View继承BaseView
     */
    interface View extends BaseView {
        void getNewsSuccess(HomeDataBean homeDataBean);
    }
    /**
     * 定义接口Presenter继承BansPresenter
     * BasePresenter 类型是一个BaseView类型，需要传入泛型值  BaseView类型的View
     */
    interface Presenter extends BasePresenter<View> {
        void getNews(String type,String key);
    }
}

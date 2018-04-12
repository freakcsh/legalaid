package com.freak.legalaid.model_home;


import com.freak.legalaid.app.ApiServer;
import com.freak.legalaid.bean.MemberRank;
import com.freak.legalaid.library.rxjava.ApiCallback;
import com.freak.legalaid.library.rxjava.HttpMethods;
import com.freak.legalaid.library.rxjava.HttpResultFunc;
import com.freak.legalaid.library.rxjava.RxPresenter;
import com.freak.legalaid.library.rxjava.SubscriberCallBack;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by William on 2017/2/20.
 */

public class MainPresenterV2 extends RxPresenter<MainContractV2.View> implements MainContractV2.Presenter {

    private ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);


    @Override
    public void searchMember(String token, String phone) {

    }

    @Override
    public void couponCodes(String phone, String id) {

    }

    @Override
    public void ensureDiscount(String code, String ecoin, String memberId, String payPassword, String token) {

    }

    @Override
    public void getPaymentMethod(String token) {

    }

    @Override
    public void memberRanks(String token) {
        Observable observable = apiServer.memberRanks(token).map(new HttpResultFunc<List<MemberRank>>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<List<MemberRank>>() {
            @Override
            public void onSuccess(List<MemberRank> model) {
                if (model != null) {
                    mView.getMemberRankSuccess(model);
                }else {
                    mView.getMemberRankSuccess(new ArrayList<MemberRank>(0));
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.showError(msg);
            }
        }));
    }

}

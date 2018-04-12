package com.freak.legalaid.model_home;


import com.freak.legalaid.bean.MemberRank;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.freak.legalaid.library.rxjava.BaseView;

import java.util.List;

/**
 * Created by hboxs006 on 2017/2/20.
 */

public interface MainContractV2 {

    interface View extends BaseView {



        void getMemberRankSuccess(List<MemberRank> memberRanks);
    }

    interface Presenter extends BasePresenter<View> {

        void searchMember(String token, String phone);

        void couponCodes(String phone, String id);

        void ensureDiscount(String code, String ecoin, String memberId, String payPassword, String token);

        void getPaymentMethod(String token);

        void memberRanks(String token);
    }
}

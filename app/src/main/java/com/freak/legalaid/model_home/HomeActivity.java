package com.freak.legalaid.model_home;

import com.freak.legalaid.app.Constants;
import com.freak.legalaid.bean.HomeDataBean;
import com.freak.legalaid.library.base.BaseActivity;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {


    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getNews("shehui", Constants.NEW_KET);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }



    @Override
    public void showError(String msg) {

    }

    @Override
    public void getNewsSuccess(HomeDataBean homeDataBean) {

    }
}

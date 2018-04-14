package com.freak.legalaid.model_information;

import com.freak.legalaid.R;
import com.freak.legalaid.library.base.BaseActivity;
import com.freak.legalaid.library.rxjava.BasePresenter;

public class BillActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.account_a;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void showError(String msg) {

    }
}

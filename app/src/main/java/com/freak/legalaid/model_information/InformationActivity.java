package com.freak.legalaid.model_information;

import android.os.Bundle;

import com.freak.legalaid.R;
import com.freak.legalaid.library.base.BaseActivity;
import com.freak.legalaid.library.rxjava.BasePresenter;

public class InformationActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_information;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showError(String msg) {

    }
}

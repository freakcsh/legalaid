package com.freak.legalaid.model_home;


import android.os.Bundle;

import com.freak.legalaid.R;
import com.freak.legalaid.library.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showError(String msg) {

    }
}

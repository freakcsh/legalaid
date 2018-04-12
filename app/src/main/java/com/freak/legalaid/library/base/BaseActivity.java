package com.freak.legalaid.library.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SupportActivity;
import android.view.MenuItem;
import android.view.View;

import com.freak.legalaid.app.App;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.freak.legalaid.library.rxjava.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/4.
 * MVP activity基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BaseView {
    protected T mPresenter;
    protected Activity mActivity;
    private Unbinder mUnBinder;
    protected abstract int getLayout();

    protected abstract void initEventAndData();
    protected abstract T createPresenter();
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /**
         * 创建presenter对象
         */
        mPresenter = createPresenter();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mActivity = this;
        App.getInstance().addActivity(this);

        //活动控制器
        ActivityCollector.addActivity(this);


        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        initEventAndData();
    }

    @Override
    protected void onResume() {
        App.baseActivity = this;
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);

        /**
         * presenter 解除view订阅
         */
        if (mPresenter != null) {
            mPresenter.detachView();
        }

        mUnBinder.unbind();
        App.getInstance().removeActivity(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();//返回
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

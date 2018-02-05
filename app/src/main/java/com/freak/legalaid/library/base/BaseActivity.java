package com.freak.legalaid.library.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SupportActivity;
import android.view.MenuItem;
import android.view.View;

import com.freak.legalaid.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/2/4.
 * MVP activity基类
 */

public abstract class BaseActivity extends SupportActivity implements BaseView{
    protected Activity mActivity;
    private Unbinder mUnBinder;
    protected abstract int getLayout();

    protected abstract void initEventAndData();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

//        if (mPresenter != null) {
//            mPresenter.detachView();
//        }

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

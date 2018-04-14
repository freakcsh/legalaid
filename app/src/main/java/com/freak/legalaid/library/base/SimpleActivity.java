package com.freak.legalaid.library.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;

import com.freak.legalaid.app.App;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2018/2/5.
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends SupportActivity {

    protected Activity mContext;
//    private Unbinder mUnBinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
//        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
//        mUnBinder.unbind();
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

    public void backOnclick(View view) {
        finish();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();
//
//    public void toastShow(int resId) {
//        ToastUtil.show(resId);
//    }
//
//    public void toastShow(String text) {
//        if (text.equals("用户未登录")){
//            SPUtils.put(getApplicationContext(), Constants.TOKEN_ABLE,false);
//            RxBus.getDefault().post(new UserEvent(1, Constants.RENOVATE));
//        }
//        ToastUtil.shortShow(text);
//    }
}
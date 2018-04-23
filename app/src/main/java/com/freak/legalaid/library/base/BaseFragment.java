package com.freak.legalaid.library.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freak.legalaid.R;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.freak.legalaid.library.rxjava.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/2/4.
 * MVP Fragment基类
 */

public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements BaseView {
    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected View loadingView;

    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initEventAndData(View view);

    protected abstract void initLazyData();

    /**
     * 显示加载中view，由子类实现
     */
    protected abstract void showLoading();

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        loadingView = inflater.inflate(R.layout.loading_view, container, false);
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initEventAndData(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initLazyData();
        showLoading();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


}

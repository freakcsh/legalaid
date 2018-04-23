package com.freak.legalaid.model_home;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.freak.legalaid.R;
import com.freak.legalaid.adapter.HomeDataAdapter;
import com.freak.legalaid.app.Constants;
import com.freak.legalaid.bean.HomeDataBean;
import com.freak.legalaid.library.base.BaseFragment;

/**
 * Created by Administrator on 2018/2/25.
 */


@SuppressLint("ValidFragment")
public class HomeDetailFragment extends BaseFragment<HomePresenter> implements HomeContract.View {


    private String type;
    private HomeDataAdapter homeDataAdapter;
    private SwipeRefreshLayout srl;
    private RecyclerView recyclerView;
    private String englishType;
    private View errorView;

    public HomeDetailFragment(String type) {
        this.type = type;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_detail_fragment;
    }

    @Override
    protected void initEventAndData(View view) {
        initView(view);
        initData();
    }

    private void initData() {
        srl.setRefreshing(true);
        if ("头条".equals(type)) {
            englishType = "top";
        } else if ("社会".equals(type)) {
            englishType = "shehui";
        } else if ("国内".equals(type)) {
            englishType = "guonei";
        } else if ("娱乐".equals(type)) {
            englishType = "yule";
        } else if ("体育".equals(type)) {
            englishType = "tiyu";
        } else if ("军事".equals(type)) {
            englishType = "junshi";
        } else if ("科技".equals(type)) {
            englishType = "keji";
        } else if ("财务".equals(type)) {
            englishType = "caijing";
        } else if ("时尚".equals(type)) {
            englishType = "shishang";
        }
        srl.setRefreshing(true);
//        Log.e("freak","请求数据类型："+englishType);
        //d78b502268f7456b79fbe7228cecdd46
        mPresenter.getNews(englishType, Constants.NEW_KET);
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {
        homeDataAdapter.setEmptyView(loadingView);
    }

    private void initView(View view) {
        srl = view.findViewById(R.id.srl);
        recyclerView = view.findViewById(R.id.recycler_view);
        homeDataAdapter = new HomeDataAdapter();
        homeDataAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        /***************************设置下拉刷新**********************************/
        /**
         * 设置圈圈颜色
         */
        srl.setColorSchemeColors(Color.BLUE, Color.BLUE);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getNews(englishType, Constants.NEW_KET);
                showLoading();
            }
        });
        /**
         * 设置布局管理者
         */
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /********************************  recyclerView初始化数据  ********************************/
        /**
         * 设置适配器adapter
         */
        recyclerView.setAdapter(homeDataAdapter);

        /**
         * 设置item点击事件
         */
        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), HomeDataShowActivity.class);
                intent.putExtra("url", homeDataAdapter.getItem(position).getUrl());
                startActivity(intent);
            }
        });

        /**
         * 加载错误时点击刷新
         */
        errorView = getLayoutInflater().inflate(R.layout.view_network_error, recyclerView, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srl.setRefreshing(true);
                mPresenter.getNews(englishType, Constants.NEW_KET);
                showLoading();

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showError(String msg) {
        srl.setRefreshing(false);
        homeDataAdapter.setEmptyView(errorView);
    }

    /**
     * 获取数据成功回调
     *
     * @param homeDataBean 返回的数据
     */
    @Override
    public void getNewsSuccess(HomeDataBean homeDataBean) {
        Log.e("freak", homeDataBean.toString());
        homeDataAdapter.setNewData(homeDataBean.getData());
        srl.setRefreshing(false);
    }


}

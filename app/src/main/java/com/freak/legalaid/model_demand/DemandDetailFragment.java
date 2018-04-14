package com.freak.legalaid.model_demand;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.freak.legalaid.R;
import com.freak.legalaid.adapter.DemandAdapter;
import com.freak.legalaid.library.base.BaseFragment;
import com.freak.legalaid.library.rxjava.BasePresenter;

@SuppressLint("ValidFragment")
public class DemandDetailFragment extends BaseFragment {
    private String type;
    private RecyclerView demand_recycle;
    private View errorView;
    private DemandAdapter mDemandAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public DemandDetailFragment(String type) {
        this.type = type;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.deman_detail_fragment;
    }

    @Override
    protected void initEventAndData(View view) {
        initView(view);
        initRecycleView();
    }

    private void initRecycleView() {
        demand_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDemandAdapter = new DemandAdapter(R.layout.demand_recycle_item);
        demand_recycle.setAdapter(mDemandAdapter);
        demand_recycle.setNestedScrollingEnabled(false);

        /**
         * 加载错误时点击刷新
         */
        errorView = getLayoutInflater().inflate(R.layout.view_network_error, demand_recycle, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView(View view) {
        demand_recycle = view.findViewById(R.id.demand_recycle);
        swipeRefreshLayout = view.findViewById(R.id.srl);
        swipeRefreshLayout.setColorSchemeColors(Color.GREEN,Color.BLACK);
        /**
         * 下拉刷新监听
         */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void showError(String msg) {

    }
}

package com.freak.legalaid.model_demand;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.freak.legalaid.R;
import com.freak.legalaid.adapter.DemandAdapter;
import com.freak.legalaid.adapter.OrderAdapter;
import com.freak.legalaid.bean.DemandBean;
import com.freak.legalaid.bean.OrderBean;
import com.freak.legalaid.event.UserEvent;
import com.freak.legalaid.library.base.BaseFragment;
import com.freak.legalaid.library.net.RealmHelper;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.freak.legalaid.library.rxjava.RxBus;

import org.litepal.crud.DataSupport;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

@SuppressLint("ValidFragment")
public class DemandDetailFragment extends BaseFragment {
    private int position;
    private String type;
    private RecyclerView demand_recycle;
    private View errorView;
    private View emptyView;
    private DemandAdapter mDemandAdapter;
    private OrderAdapter mOrderAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RealmHelper mRealmHelper;
    private Subscription subscription;

    public DemandDetailFragment(String type, int position) {
        this.type = type;
        this.position = position;
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

    }

    private void initRecycleView() {
        demand_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        if ("已发布".equals(type) || "待完成".equals(type) || "已完成".equals(type) || "已过期".equals(type)) {
            mDemandAdapter = new DemandAdapter(R.layout.demand_recycle_item, type);
            demand_recycle.setAdapter(mDemandAdapter);
            demand_recycle.setNestedScrollingEnabled(false);
            /**
             * 加载错误时点击刷新
             */
            errorView = getLayoutInflater().inflate(R.layout.view_network_error, demand_recycle, false);
            errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateRes(type);
                }
            });
            emptyView = getLayoutInflater().inflate(R.layout.empty_view, demand_recycle, false);
        } else if ("待接单".equals(type) || "已接单".equals(type) || "订单完成".equals(type)) {
            mOrderAdapter = new OrderAdapter(R.layout.demand_recycle_item, type, getActivity());
            demand_recycle.setAdapter(mOrderAdapter);
            demand_recycle.setNestedScrollingEnabled(false);


            /**
             * 加载错误时点击刷新
             */
            errorView = getLayoutInflater().inflate(R.layout.view_network_error, demand_recycle, false);
            errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateRes(type);
                }
            });
            emptyView = getLayoutInflater().inflate(R.layout.empty_view, demand_recycle, false);
        }

    }

    private void initView(View view) {
        demand_recycle = view.findViewById(R.id.demand_recycle);
        swipeRefreshLayout = view.findViewById(R.id.srl);
        swipeRefreshLayout.setColorSchemeColors(Color.GREEN, Color.BLACK);
        /**
         * 下拉刷新监听
         */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateRes(type);
            }
        });

        mRealmHelper = new RealmHelper();
        initRecycleView();
        updateRes(type);
        subscription = RxBus.getDefault().tObservable(UserEvent.class).subscribe(new Action1<UserEvent>() {
            @Override
            public void call(UserEvent userEvent) {
                if (userEvent.getId() == 1) {
                    switch (position) {
                        case 0:
                            updateRes("已发布");
                            break;
                        case 1:
                            updateRes("待完成");
                            break;
                        case 2:
                            updateRes("已完成");
                            break;
                        case 3:
                            updateRes("已过期");
                            break;
                        default:
                            break;
                    }

//                    mDemandAdapter.notifyDataSetChanged();
                }
                if (userEvent.getId() == 3) {
                    switch (position) {
                        case 0:
                            updateRes("待接单");
                            break;
                        case 1:
                            updateRes("已接单");
                            break;
                        case 2:
                            updateRes("订单完成");
                            break;
                        default:
                            break;
                    }
//                    updateRes(userEvent.getName());
//                   mOrderAdapter.notifyDataSetChanged();
                    Log.e("freak", "成功接收接单信息");
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void showError(String msg) {
        if ("已发布".equals(type) || "待完成".equals(type) || "已完成".equals(type) || "已过期".equals(type)) {
            mDemandAdapter.setEmptyView(loadingView);
        } else if ("待接单".equals(type) || "已接单".equals(type) || "订单完成".equals(type)) {
            mOrderAdapter.setEmptyView(loadingView);
        }
    }

    public void updateRes(String type) {
        if ("已发布".equals(type) || "待完成".equals(type) || "已完成".equals(type) || "已过期".equals(type)) {
            List<DemandBean> demandBeans = mRealmHelper.selectDemand(type);
            int count = DataSupport.count(DemandBean.class);

            if (demandBeans.size() != 0) {
                mDemandAdapter.setNewData(demandBeans);
                swipeRefreshLayout.setRefreshing(false);
            } else {
                if (count == 0) {
                    mDemandAdapter.setEmptyView(emptyView);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    mDemandAdapter.setEmptyView(errorView);
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        } else if ("待接单".equals(type) || "已接单".equals(type) || "订单完成".equals(type)) {
            List<OrderBean> orderBeanList = mRealmHelper.selectOrder(type);
            int count = DataSupport.where("type = ?", type).count(OrderBean.class);

            if (orderBeanList.size() != 0) {
                mOrderAdapter.setNewData(orderBeanList);
                swipeRefreshLayout.setRefreshing(false);
            } else {
                if (count == 0) {
                    mOrderAdapter.setNewData(orderBeanList);
                    mOrderAdapter.setEmptyView(emptyView);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    mOrderAdapter.setEmptyView(errorView);
                    swipeRefreshLayout.setRefreshing(false);
                }

            }

        }


    }
}

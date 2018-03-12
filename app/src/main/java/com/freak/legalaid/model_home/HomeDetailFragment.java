package com.freak.legalaid.model_home;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.freak.legalaid.R;
import com.freak.legalaid.adapter.HomeDataAdapter;

/**
 * Created by Administrator on 2018/2/25.
 */


@SuppressLint("ValidFragment")
public class HomeDetailFragment extends HomeBaseFragment {


    private String type;
    private HomeDataAdapter homeDataAdapter;
    private SwipeRefreshLayout srl;
    private RecyclerView recyclerView;

    public HomeDetailFragment(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View homeDetail = inflater.inflate(R.layout.home_detail_fragment, container, false);
        srl = homeDetail.findViewById(R.id.srl);
        recyclerView=homeDetail.findViewById(R.id.recycler_view);
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
//                upData();
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
        return homeDetail;
    }


    @Override
    public void fetchData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

package com.freak.legalaid.model_information;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.freak.legalaid.R;
import com.freak.legalaid.adapter.OrderAdapter;
import com.freak.legalaid.bean.OrderBean;
import com.freak.legalaid.library.net.RealmHelper;

import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView order_recycle;
    private OrderAdapter orderAdapter;

    private View emptyView;
    private RealmHelper mRealmHelper;
    private List<OrderBean> orderBeanList;
    private TextView tvTitle;
    private TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        order_recycle = (RecyclerView) findViewById(R.id.order_recycle);
        tvTitle = (TextView) findViewById(R.id.title_name);
        tvBack = (TextView) findViewById(R.id.title_return);
        order_recycle.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new OrderAdapter(R.layout.demand_recycle_item, this);
        order_recycle.setAdapter(orderAdapter);

        emptyView = getLayoutInflater().inflate(R.layout.empty_view, order_recycle, false);
        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }


        });
        tvTitle.setText("我的订单");
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRealmHelper = new RealmHelper();
        initData();
    }


    private void initData() {
        orderBeanList = mRealmHelper.selectOrder("已接单");
        if (orderBeanList.size() == 0) {
            orderAdapter.setEmptyView(emptyView);
        } else {
            orderAdapter.setNewData(orderBeanList);
        }
    }
}

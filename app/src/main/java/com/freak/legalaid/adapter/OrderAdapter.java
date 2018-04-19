package com.freak.legalaid.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.legalaid.R;
import com.freak.legalaid.app.Constants;
import com.freak.legalaid.bean.DemandBean;
import com.freak.legalaid.bean.OrderBean;
import com.freak.legalaid.dialog.CommonTipsDialogFragment;
import com.freak.legalaid.event.UserEvent;
import com.freak.legalaid.library.net.RealmHelper;
import com.freak.legalaid.library.rxjava.RxBus;
import com.freak.legalaid.utils.DialogUtil;
import com.freak.legalaid.utils.SPUtils;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;

public class OrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
    private String type;
    private Activity activity;
    private RealmHelper mRealmHelper = new RealmHelper();

    public OrderAdapter(int layoutResId, String type, Activity activity) {
        super(layoutResId);
        this.type = type;
        this.activity = activity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final OrderBean item) {
        if (item != null) {
            helper.setText(R.id.tv_user_name, item.getUserName());
            helper.setText(R.id.tv_demand_state, item.getOrderState());
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setText(R.id.tv_reward, "酬金：￥" + item.getReward());
            helper.setText(R.id.tv_context, item.getContext());
            helper.setText(R.id.tv_address, "工作地址：" + item.getAddress());
            helper.setText(R.id.tv_start_time, "起始时间" + item.getStartTime());
            helper.setText(R.id.tv_end_time, "结束时间" + item.getEndTime());
            /**
             * 以下是律师用户订单显示
             */
            if ("待接单".equals(item.getOrderState())) {
                helper.setVisible(R.id.ll_order_msg, false);
                helper.setText(R.id.tv_btn_order, "待接单").setTextColor(R.id.tv_btn_order, R.color.colorPrimary)
                        .setBackgroundRes(R.id.tv_btn_order, R.drawable.bg_text_view_green);
                helper.setOnClickListener(R.id.tv_btn_order, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtil.showCommonDialog(activity, "温馨提示", "是否确认接此订单？请确认订单完成时间，并在此时间之前完成订单！", "取消", "确认", new CommonTipsDialogFragment.OnTipsListener() {
                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onSucceed() {
                                String orderUserName = (String) SPUtils.get(activity, Constants.REAL_M_HELPER_USERNAME, "");
                                int random = (int) (Math.random() * 10000);
                                long time = System.currentTimeMillis();
                                @SuppressLint("SimpleDateFormat")
                                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                                @SuppressLint("SimpleDateFormat")
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String snTime = df.format(time);
                                String orderTime = sdf.format(time);
                                String sn = snTime + String.valueOf(random);

                                Log.e("freak", "更新前查询+++++++++" + DataSupport.findAll(OrderBean.class).toString());

                                mRealmHelper.updateOrder(item.getId(), "已接单", item.getUserName(), item.getTitle(), item.getContext(), item.getReward(), item.getAddress(), "已接单",
                                        item.getUserImagePah(), item.getStartTime(), item.getEndTime(), orderUserName, sn, orderTime);
                                mRealmHelper.updateDemand(item.getId(), "待完成", item.getUserName(), item.getTitle(), item.getContext(), item.getReward(), item.getAddress(), "待完成",
                                        item.getUserImagePah(), item.getStartTime(), item.getEndTime(), orderUserName, sn, orderTime);
                                Log.e("freak", "更新后查询订单表" + DataSupport.findAll(OrderBean.class).toString());
                                Log.e("freak", "更新后查询需求发布表" + DataSupport.findAll(DemandBean.class).toString());
                                RxBus.getDefault().post(new UserEvent(3, "待接单"));
                            }
                        });
                    }
                });
            } else if (type.equals(item.getOrderState())) {
                helper.setVisible(R.id.ll_order_msg, true);
                helper.setText(R.id.tv_order_name, item.getOrderUserName());
                helper.setText(R.id.tv_order_time, item.getOrderTime());
                helper.setText(R.id.tv_btn_order, item.getOrderState()).setTextColor(R.id.tv_btn_order,
                        "已接单".equals(item.getOrderState()) ? Color.parseColor("#e7da1738") : Color.parseColor("#989898"))
                        .setBackgroundRes(R.id.tv_btn_order, "已接单".equals(item.getOrderState()) ? R.drawable.bg_text_view_red : R.drawable.bg_text_view_gray);
            }
        }
    }

}

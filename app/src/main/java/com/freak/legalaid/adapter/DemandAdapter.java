package com.freak.legalaid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.legalaid.R;
import com.freak.legalaid.bean.DemandBean;

public class DemandAdapter extends BaseQuickAdapter<DemandBean, BaseViewHolder> {
    private String type;


    public DemandAdapter(int layoutResId, String type) {
        super(layoutResId);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, DemandBean item) {
        helper.setText(R.id.tv_user_name, item.getUserName());
        helper.setText(R.id.tv_demand_state, item.getDemandState());
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_reward, "酬金：￥" + item.getReward());
        helper.setText(R.id.tv_context, item.getContext());
        helper.setText(R.id.tv_address, "工作地址：" + item.getAddress());
        helper.setText(R.id.tv_start_time, "起始时间" + item.getStartTime());
        helper.setText(R.id.tv_end_time, "结束时间" + item.getEndTime());
        /**
         * 普通用户需求显示
         */
        if ("已发布".equals(type)) {
            helper.setVisible(R.id.ll_order_msg, false);
            helper.setVisible(R.id.v_order_bg, false);
            helper.setVisible(R.id.ll_btn, false);
//                helper.setText(R.id.tv_btn_order, "待接单").setTextColor(R.id.tv_btn_order, Color.parseColor("#49b53f"))
//                        .setBackgroundRes(R.id.tv_btn_order, R.drawable.bg_text_view_green);
        } else if ("待完成".equals(type)) {
            helper.setVisible(R.id.ll_order_msg, true);
            helper.setText(R.id.tv_order_name, item.getOrderUserName());
            helper.setText(R.id.tv_order_time, item.getOrderTime());
            helper.setVisible(R.id.ll_btn, false);
//                helper.setText(R.id.tv_btn_order, "确认完成").setTextColor(R.id.tv_btn_order, Color.parseColor("#e7da1738"))
//                        .setBackgroundRes(R.id.tv_btn_order, R.drawable.bg_text_view_red);
        } else if ("已完成".equals(type)) {
            helper.setVisible(R.id.ll_order_msg, true);
            helper.setText(R.id.tv_order_name, item.getOrderUserName());
            helper.setText(R.id.tv_order_time, item.getOrderTime());
            helper.setVisible(R.id.ll_btn, false);
//                helper.setText(R.id.tv_btn_order, "已完成").setTextColor(R.id.tv_btn_order, Color.parseColor("#989898"))
//                        .setBackgroundRes(R.id.tv_btn_order, R.drawable.bg_text_view_gray);
        } else if ("已过期".equals(type)) {
            helper.setVisible(R.id.ll_order_msg, true);
            helper.setText(R.id.tv_order_name, item.getOrderUserName());
            helper.setText(R.id.tv_order_time, item.getOrderTime());
            helper.setVisible(R.id.ll_btn, false);
        }

    }
}

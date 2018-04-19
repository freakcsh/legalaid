package com.freak.legalaid.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.legalaid.R;
import com.freak.legalaid.bean.HomeDataBean;

/**
 * Created by Administrator on 2018/2/25.
 */

public class HomeDataAdapter extends BaseQuickAdapter<HomeDataBean.Data,BaseViewHolder>{
    public HomeDataAdapter() {
        super(R.layout.home_item_data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataBean.Data item) {
        helper.setText(R.id.tv_news_detail_title,item.getTitle());
        helper.setText(R.id.tv_news_detail_author_name,item.getAuthor_name());
        helper.setText(R.id.tv_news_detail_date,item.getDate());
        /**
         * 这是item点击事件
         */
        helper.addOnClickListener(R.id.ll_news_detail);
        Glide.with(mContext)
                .load(item.getThumbnail_pic_s())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .centerCrop()
                .into((ImageView) helper.getView(R.id.iv_news_detail_pic));
    }
    }

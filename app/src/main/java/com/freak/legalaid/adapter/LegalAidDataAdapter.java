package com.freak.legalaid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.legalaid.R;
import com.freak.legalaid.bean.LegalAidBean;

/**
 * Created by Administrator on 2017/12/13.
 */

public class LegalAidDataAdapter extends BaseQuickAdapter<LegalAidBean,BaseViewHolder>{
    public LegalAidDataAdapter() {
        super(R.layout.common_new_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, LegalAidBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_context,item.getContext());
        helper.setText(R.id.tv_time,item.getIssueTime());
        helper.addOnClickListener(R.id.ly_recycle);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}

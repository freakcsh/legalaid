package com.freak.legalaid.model_demand;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.freak.legalaid.R;
import com.freak.legalaid.app.Constants;
import com.freak.legalaid.bean.LoginCommonUserBean;
import com.freak.legalaid.dialog.CommonTipsDialogFragment;
import com.freak.legalaid.library.base.BaseFragment;
import com.freak.legalaid.library.net.RealmHelper;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.freak.legalaid.utils.DialogUtil;
import com.freak.legalaid.utils.SPUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/2/5.
 */

public class DemandFragment extends BaseFragment {
    private TextView tv_send_demand;
    private RecyclerView demand_recycle;

    private MagicIndicator demand_magic;
    private ViewPager viewPager;
    private String type[];
    private DemandViewpagerAdapter mDemandViewpagerAdapter;
    private RealmHelper mRealmHelper;
    private String demandType;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demand;
    }

    @Override
    protected void initEventAndData(View view) {
        initView(view);

    }


    private void initView(View view) {
//        demand_recycle = view.findViewById(R.id.demand_recycle);
        viewPager = view.findViewById(R.id.demand_viewpager);
        demand_magic = view.findViewById(R.id.demand_magic);
        tv_send_demand = view.findViewById(R.id.tv_send_demand);
        demandType= (String) SPUtils.get(getActivity(), Constants.TYPE, "");
        if ("common".equals(demandType)) {
            tv_send_demand.setVisibility(View.VISIBLE);
            type = getResources().getStringArray(R.array.common_type);
        } else {
            tv_send_demand.setVisibility(View.GONE);
            type = getResources().getStringArray(R.array.lawyer_type);
        }

        mDemandViewpagerAdapter = new DemandViewpagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(mDemandViewpagerAdapter);

        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return type == null ? 0 : type.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {

                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                /**
                 * 设置字体颜色
                 */
                colorTransitionPagerTitleView.setNormalColor(Color.BLACK);
                /**
                 * 设置选择是字体颜色
                 */
                colorTransitionPagerTitleView.setSelectedColor(Color.BLUE);
                /**
                 * 设置指示器文字
                 */
                colorTransitionPagerTitleView.setText(type[i]);

                colorTransitionPagerTitleView.setTextSize(14);

                /**
                 * 设置指示器点击事件
                 */
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(i);
                    }
                });
                return colorTransitionPagerTitleView;

            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                /**
                 * 设置滑动的文字底下的模型
                 */
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setLineWidth(UIUtil.dip2px(context, 10));
                return linePagerIndicator;
            }
        });

        /**
         * magicIndicator设置navigator
         */
        demand_magic.setNavigator(commonNavigator);
        /**
         * magicIndicator与viewpager绑定
         */
        ViewPagerHelper.bind(demand_magic, viewPager);

        mRealmHelper=new RealmHelper();
        tv_send_demand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = (String) SPUtils.get(getActivity(), Constants.USERNAME, "");
                if ("common".equals(demandType)){
                    LoginCommonUserBean commonUserBean = DataSupport.where("userName = ?", userName).findFirst(LoginCommonUserBean.class);
                    if (TextUtils.isEmpty(commonUserBean.getRealName())){
//                        ToastUtil.shortShow("发布需求需要实名验证，请先到我的设置页面进行实名验证。");
                        DialogUtil.showCommonDialog(getActivity(), "温馨提示", "发布需求需要实名验证，请先到我的设置页面进行实名验证。", "取消", "确定", new CommonTipsDialogFragment.OnTipsListener() {
                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onSucceed() {

                            }
                        });
                    }else {
                        Intent intent=new Intent();

                    }

                }
            }
        });
    }

    @Override
    protected void initLazyData() {

    }


    @Override
    public void showError(String msg) {

    }

    public class DemandViewpagerAdapter extends FragmentStatePagerAdapter {

        public DemandViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new DemandDetailFragment(type[position]);
        }

        @Override
        public int getCount() {
            return type.length;
        }
    }
}

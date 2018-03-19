package com.freak.legalaid.model_lagal_aid_knowledge;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freak.legalaid.R;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * Created by Administrator on 2018/2/5.
 */

public class LegalAidKnowFragment extends Fragment {
    private ViewPager legal_viewpager;
    private String type[];
    private LegalAidViewpagerAdapter legalAidViewpagerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View legal = inflater.inflate(R.layout.fragment_legal_aid_know, container, false);
        legal_viewpager = legal.findViewById(R.id.legal_viewpager);
        /**
         * 指示器标题加载
         */
        type = getResources().getStringArray(R.array.legal_type_cn);
        /**
         * 初始化viewpager的adapter
         */
        legalAidViewpagerAdapter = new LegalAidViewpagerAdapter(getActivity().getSupportFragmentManager());
        legal_viewpager.setAdapter(legalAidViewpagerAdapter);
        /************************指示器加载*************************/
        MagicIndicator magicIndicator = legal.findViewById(R.id.magic_indicator);
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
                /**
                 * 设置指示器点击事件
                 */
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        legal_viewpager.setCurrentItem(i);
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
                return linePagerIndicator;
            }
        });
        /**
         * magicIndicator设置navigator
         */
        magicIndicator.setNavigator(commonNavigator);
        /**
         * magicIndicator与viewpager绑定
         */
        ViewPagerHelper.bind(magicIndicator, legal_viewpager);

        return legal;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public class LegalAidViewpagerAdapter extends FragmentStatePagerAdapter {

        public LegalAidViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new LegalAidDetailFragment(type[position]);
        }

        @Override
        public int getCount() {
            return type.length;
        }
    }
}

package com.freak.legalaid.model_home;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.freak.legalaid.R;


import com.freak.legalaid.library.base.SimpleActivity;
import com.freak.legalaid.model_demand.DemandFragment;
import com.freak.legalaid.model_information.InformationFragment;
import com.freak.legalaid.model_lagal_aid_knowledge.LegalAidKnowFragment;


public class MainActivity extends SimpleActivity implements BottomNavigationBar.OnTabSelectedListener {
    private BottomNavigationBar mBottomNavigationBar;
    FragmentManager fm;
    FragmentTransaction transaction;
    private HomeFragment mHomeFragment;
    private InformationFragment mInformationFragment;
    private LegalAidKnowFragment mLegalAidKnowFragment;
    private DemandFragment mDemandFragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        initView();
    }

    private void initView() {
        mBottomNavigationBar = findViewById(R.id.bnb);
        fm = MainActivity.this.getSupportFragmentManager();
        transaction = fm.beginTransaction();
        mHomeFragment = new HomeFragment();
        //设置BottomNavigationBar的mode
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //设置BottomNavigationBar的样式style
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "首页").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.legal, "法律知识").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.drawable.demand, "需求").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.setting, "设置").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
        transaction.add(R.id.fl_layout, mHomeFragment, "");
        transaction.commit();
        //http://blog.csdn.net/qq_16131393/article/details/51419901

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bmob.initialize(this, Constants.BMOB_APP_ID,"Bmob");
    }

    //以下三个是bottomNavigationBar的监听
    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                showHomeFragment();
                break;
            case 1:
                showLegalAidKnowledgeFragment();
                break;
            case 2:
                showDemandFragment();
                break;
            case 3:
                showSettingFragment();
                break;
            default:
                break;
        }

    }


    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 隐藏所有的fragment
     */
    private void hideAllFragment() {
        FragmentTransaction hideTransaction = fm.beginTransaction();
        if (mHomeFragment != null) {
            hideTransaction.hide(mHomeFragment);
        }
        if (mLegalAidKnowFragment != null) {
            hideTransaction.hide(mLegalAidKnowFragment);
        }
        if (mDemandFragment != null) {
            hideTransaction.hide(mDemandFragment);
        }
        if (mInformationFragment != null) {
            hideTransaction.hide(mInformationFragment);
        }
        hideTransaction.commit();
    }

    private void showSettingFragment() {
        hideAllFragment();
        if (mInformationFragment == null) {
            mInformationFragment = new InformationFragment();
            fm.beginTransaction().add(R.id.fl_layout, mInformationFragment).commit();
        } else {
            fm.beginTransaction().show(mInformationFragment).commit();
        }
    }

    private void showDemandFragment() {
        hideAllFragment();
        if (mDemandFragment == null) {
            mDemandFragment = new DemandFragment();
            fm.beginTransaction().add(R.id.fl_layout, mDemandFragment).commit();
        } else {
            fm.beginTransaction().show(mDemandFragment).commit();
        }
    }

    private void showLegalAidKnowledgeFragment() {
        hideAllFragment();
        if (mLegalAidKnowFragment == null) {
            mLegalAidKnowFragment = new LegalAidKnowFragment();
            fm.beginTransaction().add(R.id.fl_layout, mLegalAidKnowFragment).commit();
        } else {
            fm.beginTransaction().show(mLegalAidKnowFragment).commit();
        }
    }

    private void showHomeFragment() {
        hideAllFragment();
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            fm.beginTransaction().add(R.id.fl_layout, mHomeFragment).commit();
        } else {
            fm.beginTransaction().show(mHomeFragment).commit();
        }
    }
}

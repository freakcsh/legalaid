//package com.freak.legalaid.model_home;
//
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.graphics.Color;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.listener.OnItemChildClickListener;
//import com.freak.legalaid.R;
//import com.freak.legalaid.adapter.HomeDataAdapter;
//import com.freak.legalaid.bean.HomeDataBean;
//import com.freak.legalaid.library.base.BaseFragment;
//
///**
// * Created by Administrator on 2018/2/25.
// */
//
//
//@SuppressLint("ValidFragment")
//public class HomeDetailFragment extends BaseFragment<HomePresenter> implements HomeContract.View {
//
//
//    private String type;
//    private HomeDataAdapter homeDataAdapter;
//    private SwipeRefreshLayout srl;
//    private RecyclerView recyclerView;
//    private String englishType;
//
//    public HomeDetailFragment(String type) {
//        this.type = type;
//    }
//
//    @Override
//    protected HomePresenter createPresenter() {
//        return new HomePresenter();
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.home_detail_fragment;
//    }
//
//    @Override
//    protected void initEventAndData(View view) {
//        initView(view);
//    }
//
//    @Override
//    protected void initLazyData() {
//        srl.setRefreshing(true);
//        if ("头条".equals(type)) {
//            englishType = "top";
//        } else if ("社会".equals(type)) {
//            englishType = "shehui";
//        } else if ("国内".equals(type)) {
//            englishType = "guonei";
//        } else if ("娱乐".equals(type)) {
//            englishType = "yule";
//        } else if ("体育".equals(type)) {
//            englishType = "tiyu";
//        } else if ("军事".equals(type)) {
//            englishType = "junshi";
//        } else if ("科技".equals(type)) {
//            englishType = "keji";
//        } else if ("财务".equals(type)) {
//            englishType = "caijing";
//        } else if ("时尚".equals(type)) {
//            englishType = "shishang";
//        }
//        srl.setRefreshing(true);
//        Log.e("freak","请求数据类型："+englishType);
////        mPresenter.getNews(englishType, Constants.NEW_KET);
//    }
//
//    private void initView(View view) {
//        srl = view.findViewById(R.id.srl);
//        recyclerView = view.findViewById(R.id.recycler_view);
//        homeDataAdapter = new HomeDataAdapter();
//        homeDataAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
//        /***************************设置下拉刷新**********************************/
//        /**
//         * 设置圈圈颜色
//         */
//        srl.setColorSchemeColors(Color.BLUE, Color.BLUE);
//        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                upData();
//                initLazyData();
//            }
//        });
//        /**
//         * 设置布局管理者
//         */
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        /********************************  recyclerView初始化数据  ********************************/
//        /**
//         * 设置适配器adapter
//         */
//        recyclerView.setAdapter(homeDataAdapter);
//
//        /**
//         * 设置item点击事件
//         */
//        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
//            @Override
//            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), HomeDataShowActivity.class);
////                intent.putExtra("url", homeDataAdapter.getItem(position).getUrl());
//                startActivity(intent);
//            }
//        });
//    }
//
//
////    @Nullable
////    @Override
////    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
////        View homeDetail = inflater.inflate(R.layout.home_detail_fragment, container, false);
////        srl = homeDetail.findViewById(R.id.srl);
////        recyclerView = homeDetail.findViewById(R.id.recycler_view);
////        homeDataAdapter = new HomeDataAdapter();
////        homeDataAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
////        /***************************设置下拉刷新**********************************/
////        /**
////         * 设置圈圈颜色
////         */
////        srl.setColorSchemeColors(Color.BLUE, Color.BLUE);
////        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
////            @Override
////            public void onRefresh() {
//////                upData();
////            }
////        });
////        /**
////         * 设置布局管理者
////         */
////        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
////        /********************************  recyclerView初始化数据  ********************************/
////        /**
////         * 设置适配器adapter
////         */
////        recyclerView.setAdapter(homeDataAdapter);
////
////        /**
////         * 设置item点击事件
////         */
////        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
////            @Override
////            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
////                Intent intent = new Intent();
////                intent.setClass(getActivity(), HomeDataShowActivity.class);
////                intent.putExtra("url", homeDataAdapter.getItem(position).getUrl());
////                startActivity(intent);
////            }
////        });
////        return homeDetail;
////    }
//
//
////    private void upData() {
////        srl.setRefreshing(true);
////        HomeNewClient.getInstance().getNewsData(type, new HomeNewCallBack<HomeDataBean>() {
////            @Override
////            public void onSuccess(HomeDataBean response, int id) {
////                homeDataAdapter.setNewData(response.getResult().getData());
////                srl.setRefreshing(false);
////            }
////
////            @Override
////            public void onError(Exception e, int id) {
////                srl.setRefreshing(false);
////            }
////        });
////    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//    }
//
//    @Override
//    public void showError(String msg) {
//        srl.setRefreshing(false);
//    }
//
//    /**
//     * 获取数据成功回调
//     *
//     * @param homeDataBean 返回的数据
//     */
//    @Override
//    public void getNewsSuccess(HomeDataBean homeDataBean) {
//        Log.e("freak",homeDataBean.toString());
////        homeDataAdapter.setNewData(homeDataBean);
//
//        srl.setRefreshing(false);
//    }
//
//
//}



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
import com.freak.legalaid.bean.HomeDataBean;
import com.freak.legalaid.library.net.HomeNewCallBack;
import com.freak.legalaid.library.net.HomeNewClient;

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
        recyclerView = homeDetail.findViewById(R.id.recycler_view);
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
//        upData();
    }

    private void upData() {
        srl.setRefreshing(true);
        HomeNewClient.getInstance().getNewsData(type, new HomeNewCallBack<HomeDataBean>() {
            @Override
            public void onSuccess(HomeDataBean response, int id) {
                homeDataAdapter.setNewData(response.getResult().getData());
                srl.setRefreshing(false);
            }

            @Override
            public void onError(Exception e, int id) {
                srl.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

package com.freak.legalaid.model_lagal_aid_knowledge;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.freak.legalaid.R;
import com.freak.legalaid.adapter.LegalAidDataAdapter;
import com.freak.legalaid.bean.LegalAidBean;
import com.freak.legalaid.library.net.RealmHelper;
import com.freak.legalaid.model_home.HomeBaseFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/25.
 */


@SuppressLint("ValidFragment")
public class LegalAidDetailFragment extends HomeBaseFragment {


    private String type;
    private LegalAidDataAdapter legalAidDataAdapter;
    private SwipeRefreshLayout srl;
    private RecyclerView recyclerView;
    private RealmHelper realmHelper;
    private Document document;
    private Elements element;
    private List<LegalAidBean> beanList;

    String url = "http://s.yingle.com/l/";

    public LegalAidDetailFragment(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View legalDetail = inflater.inflate(R.layout.legal_detail_fragment, container, false);
        srl = legalDetail.findViewById(R.id.srl);
        recyclerView = legalDetail.findViewById(R.id.legal_recycler_view);
        initList();
        realmHelper = new RealmHelper();
        legalAidDataAdapter = new LegalAidDataAdapter();
        legalAidDataAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        /***************************设置下拉刷新**********************************/
        /**
         * 设置圈圈颜色
         */
        srl.setColorSchemeColors(Color.BLUE, Color.BLUE);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                upData(type);
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
        recyclerView.setAdapter(legalAidDataAdapter);


        /**
         * 设置item点击事件
         */
        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), LegalAidKnowledgeActivity.class);
                intent.putExtra("url", legalAidDataAdapter.getItem(position).getUrl());
                startActivity(intent);
            }
        });
        return legalDetail;
    }

    /**
     * 初始化list
     */
    private void initList() {
        beanList = new ArrayList<>();
//        bxList = new ArrayList<>();
//        csList = new ArrayList<>();
//        gjList = new ArrayList<>();
//        jzList = new ArrayList<>();
//        msList = new ArrayList<>();
//        ssList = new ArrayList<>();
//        wqList = new ArrayList<>();
//        xfList = new ArrayList<>();
//        ztList = new ArrayList<>();
//        zwList = new ArrayList<>();
    }

    /**
     * 获取数据
     */
    @Override
    public void fetchData() {
        upData(type);
    }

    /**
     * 刷新数据
     *
     * @param type
     */
    private void upData(String type) {
        switch (type) {
            case "赔偿法":
                String pcUrl = "pc/";
                getData(url, pcUrl, 30, "pc");
                break;
            case "债务纠纷":
                String zwUrl = "zw/";
                getData(url, zwUrl, 30, "zw");
                break;
            case "刑法":
                String xfUrl = "xf/";
                getData(url, xfUrl, 30, "xf");
                break;
            case "财税法":
                String csUrl = "cs/";
                getData(url, csUrl, 30, "cs");
                break;
            case "民商法":
                String msUrl = "ms/";
                getData(url, msUrl, 30, "ms");
                break;
            case "物权法":
                String wqUrl = "wq/";
                getData(url, wqUrl, 30, "wq");
                break;
            case "建筑工程纠纷":
                String jzUrl = "jz/";
                getData(url, jzUrl, 30, "jz");
                break;
            case "保险法":
                String bxUrl = "bx/";
                getData(url, bxUrl, 30, "bx");
                break;
            case "国际法":
                String gjUrl = "gj/";
                getData(url, gjUrl, 30, "gj");
                break;
            case "诉讼法":
                String ssUrl = "ss/";
                getData(url, ssUrl, 30, "ss");
                break;
            case "法律知识专题":
                String ztUrl = "zt/";
                getData(url, ztUrl, 18, "zt");
                break;
            default:
                break;
        }

    }

    /**
     * 获取数据
     *
     * @param url
     * @param urlType
     * @param pageSize
     * @param sqlDataType
     */
    private void getData(final String url, final String urlType, final int pageSize, final String sqlDataType) {
        if (!realmHelper.isExist(sqlDataType)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getResFromHtml(url, urlType, pageSize, sqlDataType);
                }
            }).start();
            legalAidDataAdapter.setNewData(beanList);
        } else {
            getResFromSQL(sqlDataType);
            legalAidDataAdapter.setNewData(beanList);
        }
    }

    /**
     * 从网页上获取数据
     *
     * @param url
     * @param urlType
     * @param pageSize
     * @param sqlDataType
     * @return
     */
    public List<LegalAidBean> getResFromHtml(String url, String urlType, int pageSize, String sqlDataType) {
        for (int i = 0; i <= pageSize; i++) {
            if (i == 0) {
                try {
                    String realUrl = url + urlType;
                    document = Jsoup.connect(realUrl).get();
                    addToList(sqlDataType);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                String realUrl = url + urlType + "s" + i + ".html";
                Log.e("freak", "分页地址" + realUrl);
                try {
                    document = Jsoup.connect(realUrl).get();
                    addToList(sqlDataType);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        realmHelper.addCompensate(beanList);


//        legalAidDataAdapter.setNewData(beanList);
        Log.e("freak", "保存之后查询赔偿法数据库：" + realmHelper.selectAllCompensate(sqlDataType).toString());
//        switch (sqlDataType) {
//            //赔偿法
//            case "pc":
//                realmHelper.addCompensate(beanList);
//                legalAidDataAdapter.setNewData(beanList);
//                Log.e("freak", "保存之后查询赔偿法数据库：" + realmHelper.selectAllCompensate().toString());
//                break;
//            //债务纠纷
//            case "zw":
//                realmHelper.addZWData(zwList);
//                Log.e("freak", "保存之后查询债务纠纷数据库：" + realmHelper.selectAllZW().toString());
//                break;
//            //刑法
//            case "xf":
//                realmHelper.addXFData(xfList);
//                Log.e("freak", "保存之后查询刑法数据库：" + realmHelper.selectAllXF().toString());
//                break;
//            //财税法
//            case "cs":
//                realmHelper.addCSData(csList);
//                Log.e("freak", "保存之后查询财税法数据库：" + realmHelper.selectAllCS().toString());
//                break;
//            //民商法
//            case "ms":
//                realmHelper.addMSData(msList);
//                Log.e("freak", "保存之后查询民商法数据库：" + realmHelper.selectAllMS().toString());
//                break;
//            //物权法
//            case "wq":
//                realmHelper.addWQData(wqList);
//                Log.e("freak", "保存之后查询物权法数据库：" + realmHelper.selectAllWQ().toString());
//                break;
//            //建筑工程纠纷
//            case "jz":
//                realmHelper.addJZData(jzList);
//                Log.e("freak", "保存之后查询建筑工程纠纷数据库：" + realmHelper.selectAllJZ().toString());
//                break;
//            //保险法
//            case "bx":
//                realmHelper.addBxXData(bxList);
//                Log.e("freak", "保存之后查询保险法数据库：" + realmHelper.selectAllBX().toString());
//                break;
//            //国际法
//            case "gj":
//                realmHelper.addGJData(gjList);
//                Log.e("freak", "保存之后查询国际法数据库：" + realmHelper.selectAllGJ().toString());
//                break;
//            //诉讼法
//            case "ss":
//                realmHelper.addSSData(ssList);
//                Log.e("freak", "保存之后查询诉讼法数据库：" + realmHelper.selectAllSS().toString());
//                break;
//            //法律知识专题
//            case "zt":
//                realmHelper.addZTData(ztList);
//                Log.e("freak", "保存之后查询法律知识专题数据库：" + realmHelper.selectAllZT().toString());
//                break;
//            default:
//                break;
//        }

        return beanList;
    }

    /**
     * 添加到对应的list
     *
     * @param sqlDataType
     */
    private void addToList(String sqlDataType) {
        element = document.select("div.ym-jreb9u-item");
        for (Element e : element) {
            LegalAidBean legalAidBean = new LegalAidBean();
            legalAidBean.setUrl(e.select("a").attr("href"));
            legalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
            legalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
            legalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
            legalAidBean.setType(sqlDataType);
            beanList.add(legalAidBean);
        }
//        realmHelper.addCompensate(beanList);

//        switch (sqlDataType) {
//            //赔偿法
//            case "pc":
//                beanList.clear();
//                for (Element e : element) {
//                    LegalAidBean pcLegalAidBean = new LegalAidBean();
//                    pcLegalAidBean.setUrl(e.select("a").attr("href"));
//                    pcLegalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
//                    pcLegalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
//                    pcLegalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
//                    pcLegalAidBean.setType(sqlDataType);
//                    beanList.add(pcLegalAidBean);
//                }
//                break;
//            //债务纠纷
//            case "zw":
//                zwList.clear();
//                for (Element e : element) {
//                    ZWLegalAidBean zwLegalAidBean = new ZWLegalAidBean();
//                    zwLegalAidBean.setUrl(e.select("a").attr("href"));
//                    zwLegalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
//                    zwLegalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
//                    zwLegalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
//                    zwList.add(zwLegalAidBean);
//                }
//                break;
//            //刑法
//            case "xf":
//                xfList.clear();
//                for (Element e : element) {
//                    XFLegalAidBean xfLegalAidBean = new XFLegalAidBean();
//                    xfLegalAidBean.setUrl(e.select("a").attr("href"));
//                    xfLegalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
//                    xfLegalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
//                    xfLegalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
//                    xfList.add(xfLegalAidBean);
//                }
//                break;
//            //财税法
//            case "cs":
//                csList.clear();
//                for (Element e : element) {
//                    CSLegalAidBean csLegalAidBean = new CSLegalAidBean();
//                    csLegalAidBean.setUrl(e.select("a").attr("href"));
//                    csLegalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
//                    csLegalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
//                    csLegalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
//                    csList.add(csLegalAidBean);
//                }
//                break;
//            //民商法
//            case "ms":
//                msList.clear();
//                for (Element e : element) {
//                    MSLegalAidBean msLegalAidBean = new MSLegalAidBean();
//                    msLegalAidBean.setUrl(e.select("a").attr("href"));
//                    msLegalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
//                    msLegalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
//                    msLegalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
//                    msList.add(msLegalAidBean);
//                }
//                break;
//            //物权法
//            case "wq":
//                wqList.clear();
//                for (Element e : element) {
//                    WQLegalAidBean wqLegalAidBean = new WQLegalAidBean();
//                    wqLegalAidBean.setUrl(e.select("a").attr("href"));
//                    wqLegalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
//                    wqLegalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
//                    wqLegalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
//                    wqList.add(wqLegalAidBean);
//                }
//                break;
//            //建筑工程纠纷
//            case "jz":
//                jzList.clear();
//                for (Element e : element) {
//                    JZLegalAidBean jzLegalAidBean = new JZLegalAidBean();
//                    jzLegalAidBean.setUrl(e.select("a").attr("href"));
//                    jzLegalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
//                    jzLegalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
//                    jzLegalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
//                    jzList.add(jzLegalAidBean);
//                }
//                break;
//            //保险法
//            case "bx":
//                bxList.clear();
//                for (Element e : element) {
//                    BXLegalAidBean bxLegalAidBean = new BXLegalAidBean();
//                    bxLegalAidBean.setUrl(e.select("a").attr("href"));
//                    bxLegalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
//                    bxLegalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
//                    bxLegalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
//                    bxList.add(bxLegalAidBean);
//                }
//                break;
//            //国际法
//            case "gj":
//                gjList.clear();
//                for (Element e : element) {
//                    GJLegalAidBean gjLegalAidBean = new GJLegalAidBean();
//                    gjLegalAidBean.setUrl(e.select("a").attr("href"));
//                    gjLegalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
//                    gjLegalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
//                    gjLegalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
//                    gjList.add(gjLegalAidBean);
//                }
//                break;
//            //诉讼法
//            case "ss":
//                ssList.clear();
//                for (Element e : element) {
//                    SSLegalAidBean ssLegalAidBean = new SSLegalAidBean();
//                    ssLegalAidBean.setUrl(e.select("a").attr("href"));
//                    ssLegalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
//                    ssLegalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
//                    ssLegalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
//                    ssList.add(ssLegalAidBean);
//                }
//                break;
//            //法律知识专题
//            case "zt":
//                ztList.clear();
//                for (Element e : element) {
//                    ZTLegalAidBean ztLegalAidBean = new ZTLegalAidBean();
//                    ztLegalAidBean.setUrl(e.select("a").attr("href"));
//                    ztLegalAidBean.setTitle(e.select("div.ym-jreb9u-tit").select("a").first().text());
//                    ztLegalAidBean.setContext(e.select("div.ym-jreb9u-desp").select("a").first().text());
//                    ztLegalAidBean.setIssueTime(e.select("div.ym-jreb9u-time").first().text());
//                    ztList.add(ztLegalAidBean);
//                }
//                break;
//            default:
//                break;
//        }

    }

    /**
     * 从数据库获取数据
     *
     * @return
     */
    public void getResFromSQL(String sqlDataType) {
        beanList.clear();
        beanList = realmHelper.selectAllCompensate(sqlDataType);
        Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.where("type = ?", sqlDataType).count(LegalAidBean.class)));
        Log.e("freak", "数据库数据：" + beanList.toString());

//        switch (sqlDataType) {
//            //赔偿法
//            case "pc":
//                beanList.clear();
//                beanList = realmHelper.selectAllCompensate();
//                Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.count(LegalAidBean.class)));
//                Log.e("freak", "数据库数据：" + beanList.toString());
//                break;
//            //债务纠纷
//            case "zw":
//                zwList.clear();
//                zwList=realmHelper.selectAllZW();
//                Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.count(ZWLegalAidBean.class)));
//                Log.e("freak", "数据库数据：" + zwList.toString());
//                break;
//            //刑法
//            case "xf":
//                xfList.clear();
//                xfList=realmHelper.selectAllXF();
//                Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.count(XFLegalAidBean.class)));
//                Log.e("freak", "数据库数据：" + xfList.toString());
//                break;
//            //财税法
//            case "cs":
//                csList.clear();
//                csList=realmHelper.selectAllCS();
//                Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.count(CSLegalAidBean.class)));
//                Log.e("freak", "数据库数据：" + csList.toString());
//                break;
//            //民商法
//            case "ms":
//                msList.clear();
//                msList=realmHelper.selectAllMS();
//                Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.count(MSLegalAidBean.class)));
//                Log.e("freak", "数据库数据：" + msList.toString());
//                break;
//            //物权法
//            case "wq":
//                wqList.clear();
//                wqList=realmHelper.selectAllWQ();
//                Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.count(WQLegalAidBean.class)));
//                Log.e("freak", "数据库数据：" + wqList.toString());
//                break;
//            //建筑工程纠纷
//            case "jz":
//                jzList.clear();
//                jzList=realmHelper.selectAllJZ();
//                Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.count(JZLegalAidBean.class)));
//                Log.e("freak", "数据库数据：" + jzList.toString());
//                break;
//            //保险法
//            case "bx":
//                bxList.clear();
//                bxList=realmHelper.selectAllBX();
//                Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.count(BXLegalAidBean.class)));
//                Log.e("freak", "数据库数据：" + bxList.toString());
//                break;
//            //国际法
//            case "gj":
//                gjList.clear();
//                gjList=realmHelper.selectAllGJ();
//                Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.count(GJLegalAidBean.class)));
//                Log.e("freak", "数据库数据：" + gjList.toString());
//                break;
//            //诉讼法
//            case "ss":
//                ssList.clear();
//                ssList=realmHelper.selectAllSS();
//                Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.count(SSLegalAidBean.class)));
//                Log.e("freak", "数据库数据：" + ssList.toString());
//                break;
//            //法律知识专题
//            case "zt":
//                ztList.clear();
//                ztList=realmHelper.selectAllZT();
//                Log.e("freak", "数据库数据总数：" + String.valueOf(DataSupport.count(ZTLegalAidBean.class)));
//                Log.e("freak", "数据库数据：" + ztList.toString());
//                break;
//            default:
//                break;
//        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

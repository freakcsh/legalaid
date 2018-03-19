package com.freak.legalaid.library.net;

import com.freak.legalaid.bean.LegalAidBean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by hboxs028 on 2018/3/17.
 */

public class RealmHelper {
    public RealmHelper() {
    }

    /**
     * 保存所有的赔偿法
     *
     * @param legalAidBeans
     */
    public void addCompensate(List<LegalAidBean> legalAidBeans) {
        DataSupport.saveAll(legalAidBeans);
    }

    /**
     * 查询所有的赔偿法
     *
     * @return
     */
    public List<LegalAidBean> selectAllCompensate(String type) {
        List<LegalAidBean> all = DataSupport.where("type = ?", type).find(LegalAidBean.class);
        return all;
    }

//    /**
//     * 保存所有的债务法
//     *
//     * @param zwLegalAidBeans
//     */
//    public void addZWData(List<ZWLegalAidBean> zwLegalAidBeans) {
//        DataSupport.saveAll(zwLegalAidBeans);
//    }
//
//    /**
//     * 查询所有的债务法
//     *
//     * @return
//     */
//    public List<ZWLegalAidBean> selectAllZW() {
//        List<ZWLegalAidBean> all = DataSupport.findAll(ZWLegalAidBean.class);
//        return all;
//    }
//
//    /**
//     * 保存所有的刑法
//     *
//     * @param xfLegalAidBeans
//     */
//    public void addXFData(List<XFLegalAidBean> xfLegalAidBeans) {
//        DataSupport.saveAll(xfLegalAidBeans);
//    }
//
//    /**
//     * 查询所有的刑法
//     *
//     * @return
//     */
//    public List<XFLegalAidBean> selectAllXF() {
//        List<XFLegalAidBean> all = DataSupport.findAll(XFLegalAidBean.class);
//        return all;
//    }
//
//    /**
//     * 保存所有的财税法
//     *
//     * @param csLegalAidBeans
//     */
//    public void addCSData(List<CSLegalAidBean> csLegalAidBeans) {
//        DataSupport.saveAll(csLegalAidBeans);
//    }
//
//    /**
//     * 查询所有的债财税法
//     *
//     * @return
//     */
//    public List<CSLegalAidBean> selectAllCS() {
//        List<CSLegalAidBean> all = DataSupport.findAll(CSLegalAidBean.class);
//        return all;
//    }
//
//    /**
//     * 保存所有的民商法
//     *
//     * @param msLegalAidBeans
//     */
//    public void addMSData(List<MSLegalAidBean> msLegalAidBeans) {
//        DataSupport.saveAll(msLegalAidBeans);
//    }
//
//    /**
//     * 查询所有的民商法
//     *
//     * @return
//     */
//    public List<MSLegalAidBean> selectAllMS() {
//        List<MSLegalAidBean> all = DataSupport.findAll(MSLegalAidBean.class);
//        return all;
//    }
//
//    /**
//     * 保存所有的物权法
//     *
//     * @param wqLegalAidBeans
//     */
//    public void addWQData(List<WQLegalAidBean> wqLegalAidBeans) {
//        DataSupport.saveAll(wqLegalAidBeans);
//    }
//
//    /**
//     * 查询所有的物权法
//     *
//     * @return
//     */
//    public List<WQLegalAidBean> selectAllWQ() {
//        List<WQLegalAidBean> all = DataSupport.findAll(WQLegalAidBean.class);
//        return all;
//    }
//
//    /**
//     * 保存所有的建筑工程纠纷法
//     *
//     * @param jzLegalAidBeans
//     */
//    public void addJZData(List<JZLegalAidBean> jzLegalAidBeans) {
//        DataSupport.saveAll(jzLegalAidBeans);
//    }
//
//    /**
//     * 查询所有的建筑工程纠纷法
//     *
//     * @return
//     */
//    public List<JZLegalAidBean> selectAllJZ() {
//        List<JZLegalAidBean> all = DataSupport.findAll(JZLegalAidBean.class);
//        return all;
//    }
//
//    /**
//     * 保存所有的保险法
//     *
//     * @param bxLegalAidBeans
//     */
//    public void addBxXData(List<BXLegalAidBean> bxLegalAidBeans) {
//        DataSupport.saveAll(bxLegalAidBeans);
//    }
//
//    /**
//     * 查询所有的保险法
//     *
//     * @return
//     */
//    public List<BXLegalAidBean> selectAllBX() {
//        List<BXLegalAidBean> all = DataSupport.findAll(BXLegalAidBean.class);
//        return all;
//    }
//
//    /**
//     * 保存所有的国际法
//     *
//     * @param gjLegalAidBeans
//     */
//    public void addGJData(List<GJLegalAidBean> gjLegalAidBeans) {
//        DataSupport.saveAll(gjLegalAidBeans);
//    }
//
//    /**
//     * 查询所有的债务法
//     *
//     * @return
//     */
//    public List<GJLegalAidBean> selectAllGJ() {
//        List<GJLegalAidBean> all = DataSupport.findAll(GJLegalAidBean.class);
//        return all;
//    }
//
//    /**
//     * 保存所有的诉讼法
//     *
//     * @param ssLegalAidBeans
//     */
//    public void addSSData(List<SSLegalAidBean> ssLegalAidBeans) {
//        DataSupport.saveAll(ssLegalAidBeans);
//    }
//
//    /**
//     * 查询所有的诉讼法
//     *
//     * @return
//     */
//    public List<SSLegalAidBean> selectAllSS() {
//        List<SSLegalAidBean> all = DataSupport.findAll(SSLegalAidBean.class);
//        return all;
//    }
//
//    /**
//     * 保存所有的法律知识专题
//     *
//     * @param ztLegalAidBeans
//     */
//    public void addZTData(List<ZTLegalAidBean> ztLegalAidBeans) {
//        DataSupport.saveAll(ztLegalAidBeans);
//    }
//
//    /**
//     * 查询所有的债务法
//     *
//     * @return
//     */
//    public List<ZTLegalAidBean> selectAllZT() {
//        List<ZTLegalAidBean> all = DataSupport.findAll(ZTLegalAidBean.class);
//        return all;
//    }

    /**
     * @param sqlDataType
     * @return
     */
    public boolean isExist(String sqlDataType) {
//        int first ;
        int first = DataSupport.where("type =?", sqlDataType).count(LegalAidBean.class);
//        switch (sqlDataType) {
//            //赔偿法
//            case "pc":
//                first = DataSupport.where("type =?",sqlDataType).count(LegalAidBean.class);
//                Log.e("freak", "赔偿法数据库总数：" + first);
//                break;
//            //债务纠纷
//            case "zw":
////                first = DataSupport.count(ZWLegalAidBean.class);
//                first = DataSupport.where("type =?",sqlDataType).count(LegalAidBean.class);
//                Log.e("freak", "债务纠纷数据库总数：" + first);
//                break;
//            //刑法
//            case "xf":
//                first = DataSupport.where("type =?",sqlDataType).count(LegalAidBean.class);
////                first = DataSupport.count(XFLegalAidBean.class);
//                Log.e("freak", "刑法数据库总数：" + first);
//                break;
//            //财税法
//            case "cs":
//                first = DataSupport.where("type =?",sqlDataType).count(LegalAidBean.class);
////                first = DataSupport.count(CSLegalAidBean.class);
//                Log.e("freak", "财税法数据库总数：" + first);
//                break;
//            //民商法
//            case "ms":
////                first = DataSupport.count(MSLegalAidBean.class);
//                first = DataSupport.where("type =?",sqlDataType).count(LegalAidBean.class);
//                Log.e("freak", "民商法数据库总数：" + first);
//                break;
//            //物权法
//            case "wq":
////                first = DataSupport.count(WQLegalAidBean.class);
//                first = DataSupport.where("type =?",sqlDataType).count(LegalAidBean.class);
//                Log.e("freak", "物权法数据库总数：" + first);
//                break;
//            //建筑工程纠纷
//            case "jz":
////                first = DataSupport.count(JZLegalAidBean.class);
//                first = DataSupport.where("type =?",sqlDataType).count(LegalAidBean.class);
//                Log.e("freak", "建筑工程纠纷数据库总数：" + first);
//                break;
//            //保险法
//            case "bx":
////                first = DataSupport.count(BXLegalAidBean.class);
//                first = DataSupport.where("type =?",sqlDataType).count(LegalAidBean.class);
//                Log.e("freak", "保险法数据库总数：" + first);
//                break;
//            //国际法
//            case "gj":
//                first = DataSupport.count(GJLegalAidBean.class);
//                Log.e("freak", "国际法数据库总数：" + first);
//                break;
//            //诉讼法
//            case "ss":
//                first = DataSupport.count(SSLegalAidBean.class);
//                Log.e("freak", "诉讼法数据库总数：" + first);
//                break;
//            //法律知识专题
//            case "zt":
//                first = DataSupport.count(ZTLegalAidBean.class);
//                Log.e("freak", "法律知识专题数据库总数：" + first);
//                break;
//            default:
//                break;
//        }
        if (first == 0) {
            return false;
        } else {
            return true;
        }
    }
}

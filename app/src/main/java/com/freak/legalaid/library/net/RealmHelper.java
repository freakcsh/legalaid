package com.freak.legalaid.library.net;

import android.util.Log;

import com.freak.legalaid.bean.LegalAidBean;
import com.freak.legalaid.bean.LoginCommonUserBean;
import com.freak.legalaid.bean.LoginLawyerUserBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hboxs028 on 2018/3/17.
 */

public class RealmHelper {


    private List<LoginCommonUserBean> loginCommonUserBeanList;
    private List<LoginLawyerUserBean> loginLawyerUserBeanList;

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

    /**
     * 查询用户是否存在
     *
     * @param userName 用户名
     * @return 返回结果
     */
    public boolean selectCommonUser(String userName) {
        loginCommonUserBeanList = DataSupport.where( "userName = ?", userName).find(LoginCommonUserBean.class);
        Log.e("freak","查询普通用户表的数据："+ loginCommonUserBeanList.toString());
            if (loginCommonUserBeanList.size()!=0){
                return true;
            }else {
                return false;
            }

    }

    /**
     * 查询律师用户表信息
     * @param userName 用户名
     * @return 返回值
     */
    public boolean selectLawyerUser(String userName){
        loginLawyerUserBeanList = DataSupport.where( "userName = ?", userName).find(LoginLawyerUserBean.class);
        Log.e("freak","查询律师用户表的数据："+ loginLawyerUserBeanList.toString());
            if (loginLawyerUserBeanList.size()!=0){
                return true;
            }else {
                return false;
            }

    }
    /**
     * 保存注册的用户
     * @param type 用户类型
     * @param userName 用户名
     * @param password 用户密码
     */
    public void addUserLogin(String type, String userName, String password) {
        if ("common".equals(type)){
            LoginCommonUserBean commonUserBean = null;
            List<LoginCommonUserBean> userBeanList = new ArrayList<>();
            userBeanList.clear();
            commonUserBean = new LoginCommonUserBean();
            commonUserBean.setType(type);
            commonUserBean.setUserName(userName);
            commonUserBean.setPassword(password);
            userBeanList.add(commonUserBean);
            DataSupport.saveAll(userBeanList);
            Log.e("freak","保存普通用户表之后查询数据："+DataSupport.findAll(LoginCommonUserBean.class).toString());
        }else if ("lawyer".equals(type)){
        LoginLawyerUserBean lawyerUserBean=null;
        List<LoginLawyerUserBean> loginLawyerUserBeanList=new ArrayList<>();
        loginLawyerUserBeanList.clear();
        lawyerUserBean.setType(type);
        lawyerUserBean.setUserName(userName);
        lawyerUserBean.setPassword(password);
        loginLawyerUserBeanList.add(lawyerUserBean);
        DataSupport.saveAll(loginLawyerUserBeanList);
        Log.e("freak","保存律师用户表之后查询数据："+DataSupport.findAll(LoginLawyerUserBean.class).toString());
        }

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

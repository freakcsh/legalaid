package com.freak.legalaid.library.net;

import android.util.Log;

import com.freak.legalaid.bean.DemandBean;
import com.freak.legalaid.bean.ImageBean;
import com.freak.legalaid.bean.LegalAidBean;
import com.freak.legalaid.bean.LoginCommonUserBean;
import com.freak.legalaid.bean.LoginLawyerUserBean;
import com.freak.legalaid.bean.OrderBean;
import com.lzy.imagepicker.bean.ImageItem;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hboxs028 on 2018/3/17.
 */

public class RealmHelper {


    private LoginLawyerUserBean lawyerUserBean;
    private LoginCommonUserBean commonUserBean;


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
        commonUserBean = DataSupport.where("userName = ?", userName).findFirst(LoginCommonUserBean.class);

        if (commonUserBean != null) {
            Log.e("freak", "查询普通用户表的数据：" + commonUserBean.toString());
            return true;
        } else {
            return false;
        }

    }

    /**
     * 查询律师用户表的用户是否存在
     *
     * @param userName 用户名
     * @return 返回值
     */
    public boolean selectLawyerUser(String userName) {
        lawyerUserBean = DataSupport.where("userName = ?", userName).findFirst(LoginLawyerUserBean.class);

        if (lawyerUserBean != null) {
            Log.e("freak", "查询律师用户表的数据：" + lawyerUserBean.toString());
            return true;
        } else {
            return false;
        }

    }

    /**
     * 保存注册的用户
     *
     * @param type     用户类型
     * @param userName 用户名
     * @param password 用户密码
     */
    public void addUserLogin(String type, String userName, String realName, String password, String sex, String age, String phone, String address) {
        if ("common".equals(type)) {
            LoginCommonUserBean commonUserBean = null;
            List<LoginCommonUserBean> userBeanList = new ArrayList<>();
            if (selectCommonUser(userName)) {
                userBeanList.clear();
                LoginCommonUserBean loginCommonUserBean = DataSupport.where("userName = ?", userName).findFirst(LoginCommonUserBean.class);
                loginCommonUserBean.setType(type);
                loginCommonUserBean.setUserName(userName);
                loginCommonUserBean.setRealName(realName);
                loginCommonUserBean.setPassword(password);
                loginCommonUserBean.setSex(sex);
                loginCommonUserBean.setAge(age);
                loginCommonUserBean.setPhone(phone);
                loginCommonUserBean.setAddress(address);
                userBeanList.add(loginCommonUserBean);
                DataSupport.saveAll(userBeanList);
                Log.e("freak", "更改普通用户表之后查询数据：" + DataSupport.findAll(LoginCommonUserBean.class).toString());
            } else {
                commonUserBean = new LoginCommonUserBean();
                commonUserBean.setType(type);
                commonUserBean.setUserName(userName);
                commonUserBean.setRealName(realName);
                commonUserBean.setPassword(password);
                commonUserBean.setSex(sex);
                commonUserBean.setAge(age);
                commonUserBean.setPhone(phone);
                commonUserBean.setAddress(address);
                userBeanList.add(commonUserBean);
                DataSupport.saveAll(userBeanList);
                Log.e("freak", "保存普通用户表之后查询数据：" + DataSupport.findAll(LoginCommonUserBean.class).toString());
            }

        } else if ("lawyer".equals(type)) {
            LoginLawyerUserBean lawyerUserBean = null;
            List<LoginLawyerUserBean> loginLawyerUserBeanList = new ArrayList<>();
            if (selectLawyerUser(userName)) {
                loginLawyerUserBeanList.clear();
                LoginLawyerUserBean loginLawyerUserBean = DataSupport.where("userName = ?", userName).findFirst(LoginLawyerUserBean.class);
                loginLawyerUserBean.setType(type);
                loginLawyerUserBean.setUserName(userName);
                loginLawyerUserBean.setRealName(realName);
                loginLawyerUserBean.setPassword(password);
                loginLawyerUserBean.setSex(sex);
                loginLawyerUserBean.setAge(age);
                loginLawyerUserBean.setPhone(phone);
                loginLawyerUserBean.setAddress(address);
                loginLawyerUserBeanList.add(loginLawyerUserBean);
                DataSupport.saveAll(loginLawyerUserBeanList);
                Log.e("freak", "更改律师用户表之后查询数据：" + DataSupport.findAll(LoginLawyerUserBean.class).toString());
            } else {
                lawyerUserBean = new LoginLawyerUserBean();
                lawyerUserBean.setType(type);
                lawyerUserBean.setUserName(userName);
                lawyerUserBean.setRealName(realName);
                lawyerUserBean.setPassword(password);
                lawyerUserBean.setSex(sex);
                lawyerUserBean.setAge(age);
                lawyerUserBean.setPhone(phone);
                lawyerUserBean.setAddress(address);
                loginLawyerUserBeanList.add(lawyerUserBean);
                DataSupport.saveAll(loginLawyerUserBeanList);
                Log.e("freak", "保存律师用户表之后查询数据：" + DataSupport.findAll(LoginLawyerUserBean.class).toString());
            }

        }

    }

    /***
     * 保存图片
     */
    public void addImage(ArrayList<ImageItem> images, String path) {
        ImageBean imageBean = null;
        List<ImageBean> list = new ArrayList<>();
        list.clear();
        imageBean = new ImageBean();
        imageBean.setImages(images);
        imageBean.setPath(path);
        list.add(imageBean);
        DataSupport.saveAll(list);
    }

    /**
     * 查询图片是否存在
     */
    public boolean selectImage() {
        ImageBean first = DataSupport.where("id = ?", "1").findFirst(ImageBean.class);
        if (first != null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 查询所有的需求
     *
     * @return
     */
    public List<DemandBean> selectDemand(String type) {
        List<DemandBean> demandBeans = DataSupport.where("type = ?", type).find(DemandBean.class);
        return demandBeans;
    }

    /**
     * 保存发布需求
     *
     * @param userName
     * @param title
     * @param context
     * @param reward
     * @param address
     * @param demandState
     * @param userImagePah
     * @param startTime
     * @param endTime
     * @param orderUserName
     * @param sn
     * @param orderTime
     */
    public void saveDemand(String type, String userName, String title, String context, String reward, String address, String demandState,
                           String userImagePah, String startTime, String endTime, String orderUserName, String sn, String orderTime) {
        DemandBean demandBean = null;
        List<DemandBean> demandBeanList = new ArrayList<>();
        demandBeanList.clear();
        demandBean = new DemandBean();
        demandBean.setType(type);
        demandBean.setUserName(userName);
        demandBean.setTitle(title);
        demandBean.setContext(context);
        demandBean.setReward(reward);
        demandBean.setAddress(address);
        demandBean.setDemandState(demandState);
        demandBean.setUserImagePah(userImagePah);
        demandBean.setStartTime(startTime);
        demandBean.setEndTime(endTime);
        demandBean.setOrderUserName(orderUserName);
        demandBean.setOrderTime(orderTime);
        demandBean.setSn(sn);
        demandBeanList.add(demandBean);
        DataSupport.saveAll(demandBeanList);
    }

    public void updateDemand(long id, String type, String userName, String title, String context, String reward, String address, String demandState,
                             String userImagePah, String startTime, String endTime, String orderUserName, String sn, String orderTime){
        List<DemandBean> demandBeanList = new ArrayList<>();
        demandBeanList.clear();
        demandBeanList = DataSupport.findAll(DemandBean.class, id);
        Log.e("freak","查询的数据"+demandBeanList.toString());
        if (demandBeanList.size() != 0) {
            for (int i = 0; i < demandBeanList.size(); i++) {
                demandBeanList.get(i).setType(type);
                demandBeanList.get(i).setUserName(userName);
                demandBeanList.get(i).setTitle(title);
                demandBeanList.get(i).setContext(context);
                demandBeanList.get(i).setReward(reward);
                demandBeanList.get(i).setAddress(address);
                demandBeanList.get(i).setDemandState(demandState);
                demandBeanList.get(i).setUserImagePah(userImagePah);
                demandBeanList.get(i).setStartTime(startTime);
                demandBeanList.get(i).setEndTime(endTime);
                demandBeanList.get(i).setOrderUserName(orderUserName);
                demandBeanList.get(i).setSn(sn);
                demandBeanList.get(i).setOrderTime(orderTime);
            }

            DataSupport.saveAll(demandBeanList);
            Log.e("freak", DataSupport.findAll(OrderBean.class).toString());

        }
    }
    /**
     * 查询发布需求的状态，是否发布成功
     *
     * @param title
     * @return
     */
    public boolean selectDemandState(String title) {
        DemandBean first = DataSupport.where("title = ?", title).findFirst(DemandBean.class);
        if (first != null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 保存订单
     *
     * @param type
     * @param userName
     * @param title
     * @param context
     * @param reward
     * @param address
     * @param orderState
     * @param userImagePah
     * @param startTime
     * @param endTime
     * @param orderUserName
     * @param sn
     * @param orderTime
     */
    public void saveOrder(String type, String userName, String title, String context, String reward, String address, String orderState,
                          String userImagePah, String startTime, String endTime, String orderUserName, String sn, String orderTime) {
        OrderBean orderBean = null;
        List<OrderBean> orderBeanList = new ArrayList<>();
        orderBean = new OrderBean();
        orderBeanList.clear();
        orderBean.setType(type);
        orderBean.setUserName(userName);
        orderBean.setTitle(title);
        orderBean.setContext(context);
        orderBean.setReward(reward);
        orderBean.setAddress(address);
        orderBean.setOrderState(orderState);
        orderBean.setUserImagePah(userImagePah);
        orderBean.setStartTime(startTime);
        orderBean.setEndTime(endTime);
        orderBean.setOrderUserName(orderUserName);
        orderBean.setSn(sn);
        orderBean.setOrderTime(orderTime);
        orderBeanList.add(orderBean);
        DataSupport.saveAll(orderBeanList);
    }

    /**
     * 更新订单
     *
     * @param type
     * @param userName
     * @param title
     * @param context
     * @param reward
     * @param address
     * @param orderState
     * @param userImagePah
     * @param startTime
     * @param endTime
     * @param orderUserName
     * @param sn
     * @param orderTime
     */
    public void updateOrder(long id, String type, String userName, String title, String context, String reward, String address, String orderState,
                            String userImagePah, String startTime, String endTime, String orderUserName, String sn, String orderTime) {
        List<OrderBean> orderBeanList = new ArrayList<>();
        orderBeanList.clear();
        orderBeanList = DataSupport.findAll(OrderBean.class, id);
        Log.e("freak","查询的数据"+orderBeanList.toString());
        if (orderBeanList.size() != 0) {
            for (int i = 0; i < orderBeanList.size(); i++) {
                orderBeanList.get(i).setType(type);
                orderBeanList.get(i).setUserName(userName);
                orderBeanList.get(i).setTitle(title);
                orderBeanList.get(i).setContext(context);
                orderBeanList.get(i).setReward(reward);
                orderBeanList.get(i).setAddress(address);
                orderBeanList.get(i).setOrderState(orderState);
                orderBeanList.get(i).setUserImagePah(userImagePah);
                orderBeanList.get(i).setStartTime(startTime);
                orderBeanList.get(i).setEndTime(endTime);
                orderBeanList.get(i).setOrderUserName(orderUserName);
                orderBeanList.get(i).setSn(sn);
                orderBeanList.get(i).setOrderTime(orderTime);
            }

            DataSupport.saveAll(orderBeanList);
            Log.e("freak", DataSupport.findAll(OrderBean.class).toString());

        }

    }

    public List<OrderBean> selectOrder(String type) {
        List<OrderBean> orderBeanList = DataSupport.where("type = ?", type).find(OrderBean.class);
        return orderBeanList;
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

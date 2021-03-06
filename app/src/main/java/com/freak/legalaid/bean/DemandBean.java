package com.freak.legalaid.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

public class DemandBean extends DataSupport implements Parcelable {
    private long id;
    private String type;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 标题
     */
    private String title;
    /**
     * 正文
     */
    private String context;
    /**
     * 发布时间
     */
    private String releaseTime;
    /**
     * 酬金
     */
    private String reward;
    /**
     * 地址
     */
    private String address;
    /**
     * 发布状态  ： 发布 、待完成、接单人发起请求确认完成，用户确认完成、已完成
     */
    private String demandState;
    /**
     * 发布用户头像地址
     */
    private String userImagePah;
    /**
     * 起始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 接单人姓名
     */
    private String orderUserName;
    /**
     * 单号
     */
    private String sn;
    /**
     * 接单时间
     */
    private String orderTime;


    public DemandBean() {
    }


    protected DemandBean(Parcel in) {
        id = in.readLong();
        type = in.readString();
        userName = in.readString();
        title = in.readString();
        context = in.readString();
        releaseTime = in.readString();
        reward = in.readString();
        address = in.readString();
        demandState = in.readString();
        userImagePah = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        orderUserName = in.readString();
        sn = in.readString();
        orderTime = in.readString();
    }

    public static final Creator<DemandBean> CREATOR = new Creator<DemandBean>() {
        @Override
        public DemandBean createFromParcel(Parcel in) {
            return new DemandBean(in);
        }

        @Override
        public DemandBean[] newArray(int size) {
            return new DemandBean[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDemandState() {
        return demandState;
    }

    public void setDemandState(String demandState) {
        this.demandState = demandState;
    }

    public String getUserImagePah() {
        return userImagePah;
    }

    public void setUserImagePah(String userImagePah) {
        this.userImagePah = userImagePah;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(type);
        dest.writeString(userName);
        dest.writeString(title);
        dest.writeString(context);
        dest.writeString(releaseTime);
        dest.writeString(reward);
        dest.writeString(address);
        dest.writeString(demandState);
        dest.writeString(userImagePah);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(orderUserName);
        dest.writeString(sn);
        dest.writeString(orderTime);
    }

    @Override
    public String toString() {
        return "DemandBean{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", reward='" + reward + '\'' +
                ", address='" + address + '\'' +
                ", demandState='" + demandState + '\'' +
                ", userImagePah='" + userImagePah + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", orderUserName='" + orderUserName + '\'' +
                ", sn='" + sn + '\'' +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}

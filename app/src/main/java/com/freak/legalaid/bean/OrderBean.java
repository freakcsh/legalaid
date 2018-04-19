package com.freak.legalaid.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

public class OrderBean extends DataSupport implements Parcelable {
    private long id;
    /**
     * 类型
     */
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
     * 订单状态
     */
    private String orderState;
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

    public OrderBean() {
    }


    protected OrderBean(Parcel in) {
        id = in.readLong();
        type = in.readString();
        userName = in.readString();
        title = in.readString();
        context = in.readString();
        releaseTime = in.readString();
        reward = in.readString();
        address = in.readString();
        orderState = in.readString();
        userImagePah = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        orderUserName = in.readString();
        sn = in.readString();
        orderTime = in.readString();
    }

    public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>() {
        @Override
        public OrderBean createFromParcel(Parcel in) {
            return new OrderBean(in);
        }

        @Override
        public OrderBean[] newArray(int size) {
            return new OrderBean[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        dest.writeString(orderState);
        dest.writeString(userImagePah);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(orderUserName);
        dest.writeString(sn);
        dest.writeString(orderTime);
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", reward='" + reward + '\'' +
                ", address='" + address + '\'' +
                ", orderState='" + orderState + '\'' +
                ", userImagePah='" + userImagePah + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", orderUserName='" + orderUserName + '\'' +
                ", sn='" + sn + '\'' +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}

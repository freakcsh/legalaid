package com.freak.legalaid.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

public class LoginLawyerUserBean extends DataSupport implements Parcelable{
    private String type;
    private String userName;
    private String password;
    private String sex;
    private String age;
    private String phone;
    private String address;

    protected LoginLawyerUserBean(Parcel in) {
        type = in.readString();
        userName = in.readString();
        password = in.readString();
        sex = in.readString();
        age = in.readString();
        phone = in.readString();
        address = in.readString();
    }

    public static final Creator<LoginLawyerUserBean> CREATOR = new Creator<LoginLawyerUserBean>() {
        @Override
        public LoginLawyerUserBean createFromParcel(Parcel in) {
            return new LoginLawyerUserBean(in);
        }

        @Override
        public LoginLawyerUserBean[] newArray(int size) {
            return new LoginLawyerUserBean[size];
        }
    };

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(sex);
        dest.writeString(age);
        dest.writeString(phone);
        dest.writeString(address);
    }
}

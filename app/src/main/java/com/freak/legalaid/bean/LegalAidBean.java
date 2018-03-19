package com.freak.legalaid.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/3/18.
 */

public class LegalAidBean extends DataSupport implements Parcelable {
    private String url;
    private String title;
    private String context;
    private String issueTime;
    private String type;

    public LegalAidBean() {
    }

    protected LegalAidBean(Parcel in) {
        url = in.readString();
        title = in.readString();
        context = in.readString();
        issueTime = in.readString();
        type = in.readString();
    }

    public static final Creator<LegalAidBean> CREATOR = new Creator<LegalAidBean>() {
        @Override
        public LegalAidBean createFromParcel(Parcel in) {
            return new LegalAidBean(in);
        }

        @Override
        public LegalAidBean[] newArray(int size) {
            return new LegalAidBean[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    @Override
    public String toString() {
        return "LegalAidBean{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", issueTime='" + issueTime + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(title);
        parcel.writeString(context);
        parcel.writeString(issueTime);
        parcel.writeString(type);
    }
}

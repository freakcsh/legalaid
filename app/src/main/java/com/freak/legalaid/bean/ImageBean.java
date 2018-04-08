package com.freak.legalaid.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.lzy.imagepicker.bean.ImageItem;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

public class ImageBean extends DataSupport implements Parcelable{
    private ArrayList<ImageItem> images;
    private  String path;

    public ImageBean() {
    }


    protected ImageBean(Parcel in) {
        images = in.createTypedArrayList(ImageItem.CREATOR);
        path = in.readString();
    }

    public static final Creator<ImageBean> CREATOR = new Creator<ImageBean>() {
        @Override
        public ImageBean createFromParcel(Parcel in) {
            return new ImageBean(in);
        }

        @Override
        public ImageBean[] newArray(int size) {
            return new ImageBean[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<ImageItem> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageItem> images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(images);
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "images=" + images +
                ", path='" + path + '\'' +
                '}';
    }
}

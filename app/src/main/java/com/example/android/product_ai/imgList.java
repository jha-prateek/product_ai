package com.example.android.product_ai;

import android.os.Parcel;
import android.os.Parcelable;

public class imgList implements Parcelable {
    private byte[] image;

    public imgList(byte[] image){
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public imgList(Parcel in) {
        image = in.createByteArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<imgList> CREATOR = new Creator<imgList>() {
        @Override
        public imgList createFromParcel(Parcel in) {
            return new imgList(in);
        }

        @Override
        public imgList[] newArray(int size) {
            return new imgList[size];
        }
    };
}

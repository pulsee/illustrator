package com.kuks.illustrator.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.kuks.illustrator.utils.Constants;

public class Asset implements Parcelable {

    private Constants.DRAWABLE_TYPE drawable_type;
    private String name;

    public Asset(String name, Constants.DRAWABLE_TYPE drawable_type) {
        this.name = name;
        this.drawable_type=drawable_type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDrawable_type(Constants.DRAWABLE_TYPE drawable_type) {
        this.drawable_type = drawable_type;
    }

    public Constants.DRAWABLE_TYPE getDrawable_type() {
        return drawable_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.drawable_type == null ? -1 : this.drawable_type.ordinal());
        dest.writeString(this.name);
    }

    protected Asset(Parcel in) {
        int tmpDrawable_type = in.readInt();
        this.drawable_type = tmpDrawable_type == -1 ? null : Constants.DRAWABLE_TYPE.values()[tmpDrawable_type];
        this.name = in.readString();
    }

    public static final Creator<Asset> CREATOR = new Creator<Asset>() {
        @Override
        public Asset createFromParcel(Parcel source) {
            return new Asset(source);
        }

        @Override
        public Asset[] newArray(int size) {
            return new Asset[size];
        }
    };
}

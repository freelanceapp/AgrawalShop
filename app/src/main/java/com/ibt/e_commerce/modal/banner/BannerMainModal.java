package com.ibt.e_commerce.modal.banner;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<BannerData> data = new ArrayList<BannerData>();
    public final static Parcelable.Creator<BannerMainModal> CREATOR = new Creator<BannerMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BannerMainModal createFromParcel(Parcel in) {
            return new BannerMainModal(in);
        }

        public BannerMainModal[] newArray(int size) {
            return (new BannerMainModal[size]);
        }

    };

    protected BannerMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (BannerData.class.getClassLoader()));
    }

    public BannerMainModal() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BannerData> getData() {
        return data;
    }

    public void setData(List<BannerData> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}
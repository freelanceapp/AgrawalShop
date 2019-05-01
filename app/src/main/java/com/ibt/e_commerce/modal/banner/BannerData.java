package com.ibt.e_commerce.modal.banner;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerData implements Parcelable {

    @SerializedName("banner_id")
    @Expose
    private String bannerId;
    @SerializedName("banner_image")
    @Expose
    private String bannerImage;
    public final static Parcelable.Creator<BannerData> CREATOR = new Creator<BannerData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BannerData createFromParcel(Parcel in) {
            return new BannerData(in);
        }

        public BannerData[] newArray(int size) {
            return (new BannerData[size]);
        }

    };

    protected BannerData(Parcel in) {
        this.bannerId = ((String) in.readValue((String.class.getClassLoader())));
        this.bannerImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public BannerData() {
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(bannerId);
        dest.writeValue(bannerImage);
    }

    public int describeContents() {
        return 0;
    }

}

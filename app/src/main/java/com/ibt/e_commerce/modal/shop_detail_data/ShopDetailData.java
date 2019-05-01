package com.ibt.e_commerce.modal.shop_detail_data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopDetailData implements Parcelable {

    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("contact_number")
    @Expose
    private String contactNumber;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("description")
    @Expose
    private String description;
    public final static Parcelable.Creator<ShopDetailData> CREATOR = new Creator<ShopDetailData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ShopDetailData createFromParcel(Parcel in) {
            return new ShopDetailData(in);
        }

        public ShopDetailData[] newArray(int size) {
            return (new ShopDetailData[size]);
        }

    };

    protected ShopDetailData(Parcel in) {
        this.shopName = ((String) in.readValue((String.class.getClassLoader())));
        this.contactNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ShopDetailData() {
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(shopName);
        dest.writeValue(contactNumber);
        dest.writeValue(address);
        dest.writeValue(description);
    }

    public int describeContents() {
        return 0;
    }

}
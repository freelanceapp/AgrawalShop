package com.ibt.e_commerce.modal.shop_detail_data;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopDetailMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<ShopDetailData> data = new ArrayList<ShopDetailData>();
    public final static Parcelable.Creator<ShopDetailMainModal> CREATOR = new Creator<ShopDetailMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ShopDetailMainModal createFromParcel(Parcel in) {
            return new ShopDetailMainModal(in);
        }

        public ShopDetailMainModal[] newArray(int size) {
            return (new ShopDetailMainModal[size]);
        }

    };

    protected ShopDetailMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (ShopDetailData.class.getClassLoader()));
    }

    public ShopDetailMainModal() {
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

    public List<ShopDetailData> getData() {
        return data;
    }

    public void setData(List<ShopDetailData> data) {
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

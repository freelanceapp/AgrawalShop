package com.ibt.e_commerce.modal.category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductImageList implements Parcelable {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    public final static Parcelable.Creator<ProductImageList> CREATOR = new Creator<ProductImageList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductImageList createFromParcel(Parcel in) {
            return new ProductImageList(in);
        }

        public ProductImageList[] newArray(int size) {
            return (new ProductImageList[size]);
        }

    };

    protected ProductImageList(Parcel in) {
        this.productId = ((String) in.readValue((String.class.getClassLoader())));
        this.productImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductImageList() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productId);
        dest.writeValue(productImage);
    }

    public int describeContents() {
        return 0;
    }

}
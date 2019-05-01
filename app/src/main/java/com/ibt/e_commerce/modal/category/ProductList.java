package com.ibt.e_commerce.modal.category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductList implements Parcelable {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_category")
    @Expose
    private String productCategory;
    @SerializedName("product_sub_category")
    @Expose
    private String productSubCategory;
    @SerializedName("product_name")
    @Expose
    private String productType;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_status")
    @Expose
    private String productStatus;
    @SerializedName("product_availablity")
    @Expose
    private String productAvailablity;
    @SerializedName("product_warranty")
    @Expose
    private String productWarranty;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("modified_date")
    @Expose
    private String modifiedDate;
    @SerializedName("product_image")
    @Expose
    private List<ProductImageList> productImage = new ArrayList<ProductImageList>();
    public final static Parcelable.Creator<ProductList> CREATOR = new Creator<ProductList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductList createFromParcel(Parcel in) {
            return new ProductList(in);
        }

        public ProductList[] newArray(int size) {
            return (new ProductList[size]);
        }

    };

    protected ProductList(Parcel in) {
        this.productId = ((String) in.readValue((String.class.getClassLoader())));
        this.productCategory = ((String) in.readValue((String.class.getClassLoader())));
        this.productSubCategory = ((String) in.readValue((String.class.getClassLoader())));
        this.productType = ((String) in.readValue((String.class.getClassLoader())));
        this.productPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.productDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.productStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.productAvailablity = ((String) in.readValue((String.class.getClassLoader())));
        this.productWarranty = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.modifiedDate = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.productImage, (ProductImageList.class.getClassLoader()));
    }

    public ProductList() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductAvailablity() {
        return productAvailablity;
    }

    public void setProductAvailablity(String productAvailablity) {
        this.productAvailablity = productAvailablity;
    }

    public String getProductWarranty() {
        return productWarranty;
    }

    public void setProductWarranty(String productWarranty) {
        this.productWarranty = productWarranty;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<ProductImageList> getProductImage() {
        return productImage;
    }

    public void setProductImage(List<ProductImageList> productImage) {
        this.productImage = productImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productId);
        dest.writeValue(productCategory);
        dest.writeValue(productSubCategory);
        dest.writeValue(productType);
        dest.writeValue(productPrice);
        dest.writeValue(productDescription);
        dest.writeValue(productStatus);
        dest.writeValue(productAvailablity);
        dest.writeValue(productWarranty);
        dest.writeValue(createdDate);
        dest.writeValue(modifiedDate);
        dest.writeList(productImage);
    }

    public int describeContents() {
        return 0;
    }

}
package com.ibt.e_commerce.modal.category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Subcategory implements Parcelable {

    @SerializedName("sub_category_id")
    @Expose
    private String subCategoryId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("sub_category_name")
    @Expose
    private String subCategoryName;
    @SerializedName("sub_category_image")
    @Expose
    private String subCategoryImage;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("products")
    @Expose
    private ArrayList<ProductList> products = new ArrayList<ProductList>();
    public final static Parcelable.Creator<Subcategory> CREATOR = new Creator<Subcategory>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Subcategory createFromParcel(Parcel in) {
            return new Subcategory(in);
        }

        public Subcategory[] newArray(int size) {
            return (new Subcategory[size]);
        }

    };

    protected Subcategory(Parcel in) {
        this.subCategoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.subCategoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.subCategoryImage = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.products, (ProductList.class.getClassLoader()));
    }

    public Subcategory() {
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryImage() {
        return subCategoryImage;
    }

    public void setSubCategoryImage(String subCategoryImage) {
        this.subCategoryImage = subCategoryImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ProductList> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductList> products) {
        this.products = products;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(subCategoryId);
        dest.writeValue(categoryId);
        dest.writeValue(subCategoryName);
        dest.writeValue(subCategoryImage);
        dest.writeValue(status);
        dest.writeList(products);
    }

    public int describeContents() {
        return 0;
    }

}

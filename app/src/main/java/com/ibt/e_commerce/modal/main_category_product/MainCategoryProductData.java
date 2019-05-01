package com.ibt.e_commerce.modal.main_category_product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ibt.e_commerce.modal.category.ProductList;

import java.util.ArrayList;
import java.util.List;

public class MainCategoryProductData implements Parcelable {

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("category_status")
    @Expose
    private String categoryStatus;
    @SerializedName("products")
    @Expose
    private List<ProductList> products = new ArrayList<>();
    public final static Parcelable.Creator<MainCategoryProductData> CREATOR = new Creator<MainCategoryProductData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MainCategoryProductData createFromParcel(Parcel in) {
            return new MainCategoryProductData(in);
        }

        public MainCategoryProductData[] newArray(int size) {
            return (new MainCategoryProductData[size]);
        }

    };

    protected MainCategoryProductData(Parcel in) {
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryImage = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryStatus = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.products, (ProductList.class.getClassLoader()));
    }

    public MainCategoryProductData() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public List<ProductList> getProducts() {
        return products;
    }

    public void setProducts(List<ProductList> products) {
        this.products = products;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(categoryId);
        dest.writeValue(categoryName);
        dest.writeValue(categoryImage);
        dest.writeValue(categoryStatus);
        dest.writeList(products);
    }

    public int describeContents() {
        return 0;
    }

}
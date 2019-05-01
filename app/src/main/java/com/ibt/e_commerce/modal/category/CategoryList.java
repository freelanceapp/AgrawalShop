package com.ibt.e_commerce.modal.category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryList implements Parcelable {

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
    @SerializedName("subcategory")
    @Expose
    private List<Subcategory> subcategory = new ArrayList<Subcategory>();
    public final static Parcelable.Creator<CategoryList> CREATOR = new Creator<CategoryList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryList createFromParcel(Parcel in) {
            return new CategoryList(in);
        }

        public CategoryList[] newArray(int size) {
            return (new CategoryList[size]);
        }

    };

    protected CategoryList(Parcel in) {
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryImage = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryStatus = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.subcategory, (com.ibt.e_commerce.modal.category.Subcategory.class.getClassLoader()));
    }

    public CategoryList() {
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

    public List<Subcategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<Subcategory> subcategory) {
        this.subcategory = subcategory;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(categoryId);
        dest.writeValue(categoryName);
        dest.writeValue(categoryImage);
        dest.writeValue(categoryStatus);
        dest.writeList(subcategory);
    }

    public int describeContents() {
        return 0;
    }

}
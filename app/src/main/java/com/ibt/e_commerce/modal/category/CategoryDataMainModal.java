package com.ibt.e_commerce.modal.category;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryDataMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<CategoryList> data = new ArrayList<CategoryList>();
    public final static Parcelable.Creator<CategoryDataMainModal> CREATOR = new Creator<CategoryDataMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryDataMainModal createFromParcel(Parcel in) {
            return new CategoryDataMainModal(in);
        }

        public CategoryDataMainModal[] newArray(int size) {
            return (new CategoryDataMainModal[size]);
        }

    };

    protected CategoryDataMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (CategoryList.class.getClassLoader()));
    }

    public CategoryDataMainModal() {
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

    public List<CategoryList> getData() {
        return data;
    }

    public void setData(List<CategoryList> data) {
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
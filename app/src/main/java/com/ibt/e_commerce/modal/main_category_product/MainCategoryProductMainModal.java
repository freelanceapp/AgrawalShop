package com.ibt.e_commerce.modal.main_category_product;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainCategoryProductMainModal implements Parcelable
{

@SerializedName("error")
@Expose
private Boolean error;
@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private List<MainCategoryProductData> data = new ArrayList<MainCategoryProductData>();
public final static Parcelable.Creator<MainCategoryProductMainModal> CREATOR = new Creator<MainCategoryProductMainModal>() {


@SuppressWarnings({
"unchecked"
})
public MainCategoryProductMainModal createFromParcel(Parcel in) {
return new MainCategoryProductMainModal(in);
}

public MainCategoryProductMainModal[] newArray(int size) {
return (new MainCategoryProductMainModal[size]);
}

}
;

protected MainCategoryProductMainModal(Parcel in) {
this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
this.message = ((String) in.readValue((String.class.getClassLoader())));
in.readList(this.data, (MainCategoryProductData.class.getClassLoader()));
}

public MainCategoryProductMainModal() {
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

public List<MainCategoryProductData> getData() {
return data;
}

public void setData(List<MainCategoryProductData> data) {
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
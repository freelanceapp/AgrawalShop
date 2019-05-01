package com.ibt.e_commerce.retrofit_provider;

import com.ibt.e_commerce.constant.Constant;
import com.ibt.e_commerce.modal.banner.BannerMainModal;
import com.ibt.e_commerce.modal.category.CategoryDataMainModal;
import com.ibt.e_commerce.modal.main_category_product.MainCategoryProductMainModal;
import com.ibt.e_commerce.modal.shop_detail_data.ShopDetailMainModal;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitApiClient {

    @GET(Constant.BANNER_API)
    Call<BannerMainModal> bannerListApi();

    @GET(Constant.CATEGORY_API)
    Call<CategoryDataMainModal> categoryListApi();

    @GET(Constant.SHOP_DETAIL_API)
    Call<ShopDetailMainModal> shopDetailApi();

    @FormUrlEncoded
    @POST(Constant.CATEGORY_PRODUCT_API)
    Call<MainCategoryProductMainModal> mainCategoryProductListApi(@Field("category_id") String category_id);


    @FormUrlEncoded
    @POST(Constant.ENQUIRY_API)
    Call<ResponseBody> enquiryApi(@Field("user_name") String user_name, @Field("user_email") String user_email,
                                  @Field("user_phone_no") String user_phone_no, @Field("product_name") String product_name,
                                  @Field("product_price") String product_price, @Field("product_description") String product_description);

}
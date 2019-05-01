package com.ibt.e_commerce.retrofit_provider;

import com.ibt.e_commerce.constant.Constant;
import com.ibt.e_commerce.modal.banner.BannerMainModal;
import com.ibt.e_commerce.modal.category.CategoryDataMainModal;
import com.ibt.e_commerce.modal.main_category_product.MainCategoryProductMainModal;

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

    @FormUrlEncoded
    @POST(Constant.CATEGORY_PRODUCT_API)
    Call<MainCategoryProductMainModal> mainCategoryProductListApi(@Field("category_id") String category_id);

}
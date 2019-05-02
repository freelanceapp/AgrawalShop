package com.ibt.e_commerce.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.e_commerce.R;
import com.ibt.e_commerce.adapter.ProductListAdapter;
import com.ibt.e_commerce.modal.category.ProductList;
import com.ibt.e_commerce.modal.main_category_product.MainCategoryProductMainModal;
import com.ibt.e_commerce.retrofit_provider.RetrofitService;
import com.ibt.e_commerce.retrofit_provider.WebResponse;
import com.ibt.e_commerce.utils.Alerts;
import com.ibt.e_commerce.utils.BaseFragment;
import com.ibt.e_commerce.utils.ConnectionDetector;

import java.util.ArrayList;

import retrofit2.Response;

public class ProductListFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private ArrayList<ProductList> productsList = new ArrayList<>();
    private String catId = "";
    private RecyclerView recyclerViewProduct;
    private ProductListAdapter productListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();

        init();
        return rootView;
    }

    private void init() {
        Bundle bundle = getArguments();

        String type = bundle.getString("type");
        if (type.equalsIgnoreCase("sub")) {
            productsList = bundle.getParcelableArrayList("data");
        } else if (type.equalsIgnoreCase("main")) {
            catId = bundle.getString("cat_id");
        }

        recyclerViewProduct = rootView.findViewById(R.id.recyclerViewProduct);

        productListAdapter = new ProductListAdapter(mContext, productsList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerViewProduct.setLayoutManager(gridLayoutManager);
        recyclerViewProduct.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProduct.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();

        if (type.equalsIgnoreCase("main")) {
            productListApi();
        }
    }

    private void productListApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getMainCategoryProductData(new Dialog(mContext), retrofitApiClient.mainCategoryProductListApi(catId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    MainCategoryProductMainModal productMainModal = (MainCategoryProductMainModal) result.body();
                    productsList.clear();
                    if (!productMainModal.getError()) {
                        if (productMainModal.getData().size() > 0) {
                            if (productMainModal.getData().get(0).getProducts().size() > 0) {
                                productsList.addAll(productMainModal.getData().get(0).getProducts());
                            }
                        }
                    } else {
                        Alerts.show(mContext, productMainModal.getMessage());
                    }
                    productListAdapter.notifyDataSetChanged();
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        } else {
            cd.show(mContext);
        }
    }

    @Override
    public void onClick(View v) {
        //startActivity(new Intent(mContext, ProductDetailsActivity.class));
    }
}

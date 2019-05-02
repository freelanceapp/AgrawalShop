package com.ibt.e_commerce.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibt.e_commerce.R;
import com.ibt.e_commerce.modal.shop_detail_data.ShopDetailMainModal;
import com.ibt.e_commerce.retrofit_provider.RetrofitService;
import com.ibt.e_commerce.retrofit_provider.WebResponse;
import com.ibt.e_commerce.utils.Alerts;
import com.ibt.e_commerce.utils.BaseFragment;
import com.ibt.e_commerce.utils.ConnectionDetector;

import retrofit2.Response;

public class AboutFragment extends BaseFragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_about, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        shopDetailAPi();
        return rootView;
    }

    private void shopDetailAPi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getShopDetailData(new Dialog(mContext), retrofitApiClient.shopDetailApi(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ShopDetailMainModal shopDetailMainModal = (ShopDetailMainModal) result.body();
                    if (!shopDetailMainModal.getError()) {
                        ((TextView) rootView.findViewById(R.id.txtDescription)).setText(shopDetailMainModal.getData().get(0).getDescription());
                        ((TextView) rootView.findViewById(R.id.txtAddress)).setText(shopDetailMainModal.getData().get(0).getAddress());
                        ((TextView) rootView.findViewById(R.id.txtContact)).setText(shopDetailMainModal.getData().get(0).getContactNumber());
                    } else {
                        Alerts.show(mContext, shopDetailMainModal.getMessage());
                    }
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
}

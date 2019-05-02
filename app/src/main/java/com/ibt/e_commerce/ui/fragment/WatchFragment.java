package com.ibt.e_commerce.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.e_commerce.R;
import com.ibt.e_commerce.retrofit_provider.RetrofitService;
import com.ibt.e_commerce.utils.BaseFragment;
import com.ibt.e_commerce.utils.ConnectionDetector;

public class WatchFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_watch, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();

        init();
        return rootView;
    }

    private void init() {
        rootView.findViewById(R.id.llProductA).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // startActivity(new Intent(mContext, ProductDetailsActivity.class));
    }
}

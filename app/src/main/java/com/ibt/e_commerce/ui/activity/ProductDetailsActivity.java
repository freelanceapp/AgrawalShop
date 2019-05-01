package com.ibt.e_commerce.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ibt.e_commerce.R;
import com.ibt.e_commerce.adapter.ViewPagerImageAdapter;
import com.ibt.e_commerce.modal.category.ProductImageList;
import com.ibt.e_commerce.modal.category.ProductList;
import com.ibt.e_commerce.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener {

    private ProductList productData;

    private ViewPager pagerSuccess;
    private ViewPagerImageAdapter pagerImageAdapter;
    private List<ProductImageList> productImageLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        init();
    }

    private void init() {
        findViewById(R.id.imgBack).setOnClickListener(this);
        productImageLists.clear();
        productData = getIntent().getParcelableExtra("product_data");

        if (productData.getProductImage().size() > 0) {
            productImageLists.addAll(productData.getProductImage());
        }
        findViewById(R.id.txtDescriptionClick).setOnClickListener(this);
        findViewById(R.id.txtWarrantyClick).setOnClickListener(this);

        initViewPager();
    }

    private void initViewPager() {
        pagerSuccess = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pagerSuccess, true);

        pagerImageAdapter = new ViewPagerImageAdapter(mContext, productImageLists, this);
        pagerSuccess.setAdapter(pagerImageAdapter);
        pagerImageAdapter.notifyDataSetChanged();

        setData();
    }

    private void setData() {
        ((TextView) findViewById(R.id.txtProductName)).setText(productData.getProductType());
        ((TextView) findViewById(R.id.txtPrice)).setText("Rs. " + productData.getProductPrice());
        ((TextView) findViewById(R.id.txtDescription)).setText(productData.getProductDescription());
        ((TextView) findViewById(R.id.txtWarrantyDescription)).setText(productData.getProductWarranty());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtDescriptionClick:
                findViewById(R.id.txtDescriptionClick).setBackgroundColor(getResources().getColor(R.color.white));
                findViewById(R.id.txtWarrantyClick).setBackgroundColor(getResources().getColor(R.color.gray_h));
                findViewById(R.id.txtDescription).setVisibility(View.VISIBLE);
                findViewById(R.id.txtWarrantyDescription).setVisibility(View.GONE);
                break;
            case R.id.txtWarrantyClick:
                findViewById(R.id.txtDescriptionClick).setBackgroundColor(getResources().getColor(R.color.gray_h));
                findViewById(R.id.txtWarrantyClick).setBackgroundColor(getResources().getColor(R.color.white));
                findViewById(R.id.txtDescription).setVisibility(View.GONE);
                findViewById(R.id.txtWarrantyDescription).setVisibility(View.VISIBLE);
                break;
            case R.id.imgBack:
                finish();
                break;
        }
    }
}

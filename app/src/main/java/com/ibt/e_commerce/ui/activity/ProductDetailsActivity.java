package com.ibt.e_commerce.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.e_commerce.R;
import com.ibt.e_commerce.adapter.ProductImageListAdapter;
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
    private ArrayList<ProductImageList> productImageLists = new ArrayList<>();
    private ProductImageListAdapter imageAdapter;
    private RecyclerView rvImage;
    private ImageView imgProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_2);

        init();
    }

    private void init() {
        findViewById(R.id.btnContact).setOnClickListener(this);
        findViewById(R.id.imgBack).setOnClickListener(this);
        rvImage = findViewById(R.id.rvImage);
        imgProduct = findViewById(R.id.imgProduct);
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

        rvImage.setLayoutManager(new LinearLayoutManager(mContext));
        imageAdapter = new ProductImageListAdapter(mContext, productImageLists, ProductDetailsActivity.this);
        rvImage.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();

        Glide.with(mContext)
                .load(productImageLists.get(0).getProductImage())
                .placeholder(R.drawable.ic_products)
                .into(imgProduct);


        /*pagerSuccess = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pagerSuccess, true);

        pagerImageAdapter = new ViewPagerImageAdapter(mContext, productImageLists, this);
        pagerSuccess.setAdapter(pagerImageAdapter);
        pagerImageAdapter.notifyDataSetChanged();*/

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
                findViewById(R.id.txtDescriptionClick).setBackgroundResource(R.drawable.btn_bg);
                findViewById(R.id.txtWarrantyClick).setBackgroundResource(R.drawable.btn_bg_gray);
                findViewById(R.id.txtDescription).setVisibility(View.VISIBLE);
                findViewById(R.id.txtWarrantyDescription).setVisibility(View.GONE);
                break;
            case R.id.txtWarrantyClick:
                findViewById(R.id.txtDescriptionClick).setBackgroundResource(R.drawable.btn_bg_gray);
                findViewById(R.id.txtWarrantyClick).setBackgroundResource(R.drawable.btn_bg);
                findViewById(R.id.txtDescription).setVisibility(View.GONE);
                findViewById(R.id.txtWarrantyDescription).setVisibility(View.VISIBLE);
                break;
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnContact:
                Intent intent = new Intent(mContext, ContactUsActivity.class);
                intent.putExtra("pro_name", productData.getProductType());
                intent.putExtra("pro_price", productData.getProductPrice());
                intent.putExtra("pro_desc", productData.getProductDescription());
                intent.putExtra("pro_image", productData.getProductImage().get(0).getProductImage());
                startActivity(intent);
                break;
            case R.id.ivListProduct:
                int tag = (int) v.getTag();
                Glide.with(mContext)
                        .load(productImageLists.get(tag).getProductImage())
                        .placeholder(R.drawable.ic_products)
                        .into(imgProduct);
                break;
        }
    }
}

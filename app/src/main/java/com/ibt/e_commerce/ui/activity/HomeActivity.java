package com.ibt.e_commerce.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ibt.e_commerce.R;
import com.ibt.e_commerce.adapter.CategoryListAdapter;
import com.ibt.e_commerce.constant.Constant;
import com.ibt.e_commerce.custom_interface.ClickItemInterface;
import com.ibt.e_commerce.modal.category.CategoryDataMainModal;
import com.ibt.e_commerce.modal.category.CategoryList;
import com.ibt.e_commerce.modal.category.Subcategory;
import com.ibt.e_commerce.retrofit_provider.RetrofitService;
import com.ibt.e_commerce.retrofit_provider.WebResponse;
import com.ibt.e_commerce.ui.fragment.CategoryFragment;
import com.ibt.e_commerce.ui.fragment.FlowerPotFragment;
import com.ibt.e_commerce.ui.fragment.HomeFragment;
import com.ibt.e_commerce.ui.fragment.MobileFragment;
import com.ibt.e_commerce.ui.fragment.ProductListFragment;
import com.ibt.e_commerce.ui.fragment.WatchFragment;
import com.ibt.e_commerce.utils.Alerts;
import com.ibt.e_commerce.utils.BaseActivity;
import com.ibt.e_commerce.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    //private DrawerLayout drawerLayout;
    private boolean isVisible = false;

    private RecyclerView recyclerViewCategory;
    public static Toolbar toolbar;
    private View contentView;
    private CategoryListAdapter categoryListAdapter;
    private List<CategoryList> categoryLists = new ArrayList<>();
    private CategoryDataMainModal mainModal;

    private HomeFragment homeFragment;
    public static FragmentUtils fragmentUtils;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_home);
        //setContentView(R.layout.activity_home);

        init();
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        fragmentUtils = new FragmentUtils(fragmentManager);
        findViewById(R.id.rlHome).setOnClickListener(this);
        findViewById(R.id.rlCategory).setOnClickListener(this);
        findViewById(R.id.rlAbout).setOnClickListener(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contentView = findViewById(R.id.container);
        /*drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                final float xOffset = view.getWidth() * v;
                contentView.setTranslationX(xOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                contentView.animate().setDuration(300).translationX(0).translationY(0);
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        };
        drawerLayout.addDrawerListener(toggle);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        toggle.syncState();*/

        //setRecyclerViewData();
        categoryApi();
    }

    private void setRecyclerViewData() {
        categoryListAdapter = new CategoryListAdapter(mContext, categoryLists, this, new ClickItemInterface() {
            @Override
            public void getProductDetail(int categoryPos, int subCategoryPos, Subcategory subcategoryData) {
                toolbar.setTitle(subcategoryData.getSubCategoryName());
                ProductListFragment productListFragment = new ProductListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("type", "sub_cat");
                bundle.putParcelableArrayList("data", subcategoryData.getProducts());
                productListFragment.setArguments(bundle);

                toolbar.setTitle("Product list");
                fragmentUtils.replaceFragment(productListFragment, Constant.ProductListFragment, R.id.frameLayout);
                //drawerLayout.closeDrawer(Gravity.START);
            }
        }, 1);
        recyclerViewCategory = findViewById(R.id.recyclerViewCategory);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerViewCategory.setLayoutManager(mLayoutManager);
        recyclerViewCategory.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCategory.setAdapter(categoryListAdapter);
        categoryListAdapter.notifyDataSetChanged();
        categoryApi();
    }

    private void initFragment() {
        findViewById(R.id.rlHome).setBackgroundColor(getResources().getColor(R.color.colorAccent));
        findViewById(R.id.rlCategory).setBackgroundColor(getResources().getColor(R.color.gray_h));
        findViewById(R.id.rlAbout).setBackgroundColor(getResources().getColor(R.color.gray_h));

        toolbar.setTitle("Home");
        homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", (Parcelable) mainModal);
        homeFragment.setArguments(bundle);
        fragmentUtils.replaceFragment(homeFragment, Constant.HomeFragment, R.id.frameLayout);
    }

    private void categoryApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getCategoryList(new Dialog(mContext), retrofitApiClient.categoryListApi(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    mainModal = (CategoryDataMainModal) result.body();
                    categoryLists.clear();
                    if (!mainModal.getError()) {
                        if (mainModal.getData().size() > 0) {
                            categoryLists.addAll(mainModal.getData());
                        }
                        initFragment();
                    } else {
                        Alerts.show(mContext, mainModal.getMessage());
                    }
                    //categoryListAdapter.notifyDataSetChanged();
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
    public void onBackPressed() {
        Fragment HomeFragmentTag = fragmentManager.findFragmentByTag(Constant.HomeFragment);
        Fragment MobileFragmentTag = fragmentManager.findFragmentByTag(Constant.MobileFragment);
        Fragment WatchFragmentTag = fragmentManager.findFragmentByTag(Constant.WatchFragment);
        Fragment FlowerPotFragmentTag = fragmentManager.findFragmentByTag(Constant.FlowerPotFragment);
        Fragment ProductListFragmentTag = fragmentManager.findFragmentByTag(Constant.ProductListFragment);
        Fragment CategoryFragmentTag = fragmentManager.findFragmentByTag(Constant.CategoryFragment);

        if (HomeFragmentTag != null) {
            finish();
        } else if (MobileFragmentTag != null) {
            toolbar.setTitle("Home");
            initFragment();
            //fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.frameLayout);
        } else if (WatchFragmentTag != null) {
            toolbar.setTitle("Home");
            initFragment();
            //fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.frameLayout);
        } else if (FlowerPotFragmentTag != null) {
            toolbar.setTitle("Home");
            initFragment();
            //fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.frameLayout);
        } else if (ProductListFragmentTag != null) {
            toolbar.setTitle("Home");
            initFragment();
            //fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.frameLayout);
        } else if (CategoryFragmentTag != null) {
            toolbar.setTitle("Home");
            initFragment();
            //fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.frameLayout);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llCategory:
                int pos = Integer.parseInt(v.getTag().toString());
                View view = recyclerViewCategory.getChildAt(pos);
                //ImageView imgExpand = view.findViewById(R.id.imgExpand);
                RecyclerView recyclerViewSubCategory = view.findViewById(R.id.recyclerViewSubCategory);
                if (categoryLists.get(pos).getSubcategory().size() > 0) {
                    recyclerViewSubCategory.setVisibility(View.VISIBLE);
                } else {
                    ProductListFragment productListFragment = new ProductListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "main_cat");
                    bundle.putString("cat_id", categoryLists.get(pos).getCategoryId());
                    productListFragment.setArguments(bundle);

                    fragmentUtils.replaceFragment(productListFragment, Constant.ProductListFragment, R.id.frameLayout);
                    //drawerLayout.closeDrawer(Gravity.START);
                }
                break;
            case R.id.llMobile:
                toolbar.setTitle("Mobiles");
                fragmentUtils.replaceFragment(new MobileFragment(), Constant.MobileFragment, R.id.frameLayout);
                //drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.llWatch:
                if (isVisible) {
                    findViewById(R.id.imgExpand).setRotation(0);
                    findViewById(R.id.llSubCatWatch).setVisibility(View.GONE);
                    isVisible = false;
                } else {
                    findViewById(R.id.imgExpand).setRotation(90);
                    findViewById(R.id.llSubCatWatch).setVisibility(View.VISIBLE);
                    isVisible = true;
                }
                break;
            case R.id.rlHome:
                toolbar.setTitle("Home");
                initFragment();
                //drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.rlCategory:
                findViewById(R.id.rlHome).setBackgroundColor(getResources().getColor(R.color.gray_h));
                findViewById(R.id.rlCategory).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                findViewById(R.id.rlAbout).setBackgroundColor(getResources().getColor(R.color.gray_h));
                toolbar.setTitle("Category");

                CategoryFragment categoryFragment = new CategoryFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("data", (Parcelable) mainModal);
                categoryFragment.setArguments(bundle);
                fragmentUtils.replaceFragment(categoryFragment, Constant.CategoryFragment, R.id.frameLayout);
                //drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.llFlowerPot:
                toolbar.setTitle("Flower Pots");
                fragmentUtils.replaceFragment(new FlowerPotFragment(), Constant.FlowerPotFragment, R.id.frameLayout);
                //drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.llMenWatch:
                toolbar.setTitle("Men's Watches");
                fragmentUtils.replaceFragment(new WatchFragment(), Constant.WatchFragment, R.id.frameLayout);
                //drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.llWomenWatch:
                toolbar.setTitle("Women's Watches");
                fragmentUtils.replaceFragment(new WatchFragment(), Constant.WatchFragment, R.id.frameLayout);
                //drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.llKidsWatch:
                toolbar.setTitle("Kid's Watches");
                fragmentUtils.replaceFragment(new WatchFragment(), Constant.WatchFragment, R.id.frameLayout);
                //drawerLayout.closeDrawer(Gravity.START);
                break;
        }
    }
}

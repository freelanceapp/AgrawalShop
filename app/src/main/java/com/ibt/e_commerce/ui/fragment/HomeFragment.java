package com.ibt.e_commerce.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibt.e_commerce.R;
import com.ibt.e_commerce.adapter.CategoryListAdapter;
import com.ibt.e_commerce.adapter.ViewPagerAdapter;
import com.ibt.e_commerce.constant.Constant;
import com.ibt.e_commerce.custom_interface.ClickItemInterface;
import com.ibt.e_commerce.modal.banner.BannerData;
import com.ibt.e_commerce.modal.banner.BannerMainModal;
import com.ibt.e_commerce.modal.category.CategoryDataMainModal;
import com.ibt.e_commerce.modal.category.CategoryList;
import com.ibt.e_commerce.modal.category.Subcategory;
import com.ibt.e_commerce.retrofit_provider.RetrofitService;
import com.ibt.e_commerce.retrofit_provider.WebResponse;
import com.ibt.e_commerce.utils.Alerts;
import com.ibt.e_commerce.utils.BaseFragment;
import com.ibt.e_commerce.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

import static com.ibt.e_commerce.ui.activity.HomeActivity.fragmentUtils;
import static com.ibt.e_commerce.ui.activity.HomeActivity.toolbar;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;

    private Handler imageHandler;
    private Runnable imageRunnable;
    private ViewPager pagerSuccess;
    private ViewPagerAdapter adapter;
    private List<BannerData> bannerList = new ArrayList<>();

    private RecyclerView recyclerViewCategory;
    private CategoryListAdapter categoryListAdapter;
    private List<CategoryList> categoryLists = new ArrayList<>();
    private CategoryDataMainModal mainModal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        initViewPager();
        return rootView;
    }

    private void initViewPager() {
        Bundle bundle = getArguments();
        mainModal = bundle.getParcelable("data");
        categoryLists.addAll(mainModal.getData());

        rootView.findViewById(R.id.llMobile).setOnClickListener(this);
        rootView.findViewById(R.id.llFlowerPot).setOnClickListener(this);
        rootView.findViewById(R.id.llWatch).setOnClickListener(this);

        pagerSuccess = rootView.findViewById(R.id.viewPager);
        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pagerSuccess, true);

        adapter = new ViewPagerAdapter(mContext, bannerList, this);
        pagerSuccess.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        imageHandler = new Handler();
        imageRunnable = new Runnable() {
            @Override
            public void run() {
                if (bannerList.size() > 0) {
                    pagerSlide();
                }
            }
        };
        imageHandler.postDelayed(imageRunnable, 3000);

        bannerApi();
        setRecyclerViewData();
    }

    public void pagerSlide() {
        if (pagerSuccess == null)
            return;
        int successPos = pagerSuccess.getCurrentItem();
        successPos++;
        if (successPos != bannerList.size()) {
            pagerSuccess.setCurrentItem(successPos);
            imageHandler.postDelayed(imageRunnable, 3000);
        } else {
            pagerSuccess.setCurrentItem(0);
            imageHandler.postDelayed(imageRunnable, 3000);
        }
    }

    private void bannerApi() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.getBannerList(new Dialog(mContext), retrofitApiClient.bannerListApi(), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    BannerMainModal mainModal = (BannerMainModal) result.body();
                    bannerList.clear();
                    if (!mainModal.getError()) {
                        if (mainModal.getData().size() > 0) {
                            bannerList.addAll(mainModal.getData());
                        }
                    } else {
                        Alerts.show(mContext, mainModal.getMessage());
                    }
                    adapter.notifyDataSetChanged();
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

                fragmentUtils.replaceFragment(productListFragment, Constant.ProductListFragment, R.id.frameLayout);
            }
        }, 2);
        recyclerViewCategory = rootView.findViewById(R.id.recyclerViewCategory);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerViewCategory.setLayoutManager(mLayoutManager);
        recyclerViewCategory.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCategory.setAdapter(categoryListAdapter);
        categoryListAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnView:
            case R.id.llCategory:
                int pos = Integer.parseInt(v.getTag().toString());
                View view = recyclerViewCategory.getChildAt(pos);
                // ImageView imgExpand = view.findViewById(R.id.imgExpand);
                RecyclerView recyclerViewSubCategory = view.findViewById(R.id.recyclerViewSubCategory);
                if (categoryLists.get(pos).getSubcategory().size() > 0) {
                    recyclerViewSubCategory.setVisibility(View.VISIBLE);
                } else {
                    ProductListFragment productListFragment = new ProductListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "main_cat");
                    bundle.putString("cat_id", categoryLists.get(pos).getCategoryId());
                    productListFragment.setArguments(bundle);

                    toolbar.setTitle("Product List");
                    fragmentUtils.replaceFragment(productListFragment, Constant.ProductListFragment, R.id.frameLayout);
                }
                break;
            case R.id.llMobile:
                toolbar.setTitle("Mobiles");
                fragmentUtils.replaceFragment(new MobileFragment(), Constant.MobileFragment, R.id.frameLayout);
                break;
            case R.id.llWatch:
                toolbar.setTitle("Watches");
                fragmentUtils.replaceFragment(new WatchFragment(), Constant.WatchFragment, R.id.frameLayout);
                break;
            case R.id.llFlowerPot:
                toolbar.setTitle("Flower Pots");
                fragmentUtils.replaceFragment(new FlowerPotFragment(), Constant.FlowerPotFragment, R.id.frameLayout);
                break;
        }
    }
}

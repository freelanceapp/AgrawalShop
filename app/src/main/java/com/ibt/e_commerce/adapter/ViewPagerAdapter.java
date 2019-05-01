package com.ibt.e_commerce.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.ibt.e_commerce.R;
import com.ibt.e_commerce.modal.banner.BannerData;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<BannerData> dataList;
    private View.OnClickListener onClickListener;

    public ViewPagerAdapter(Context context, List<BannerData> dataList, View.OnClickListener onClickListener) {
        this.mContext = context;
        this.dataList = dataList;
        this.onClickListener = onClickListener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.slide_show_pager_item, container, false);
        ImageView imageView = itemView.findViewById(R.id.imageView);
        String strUrl = dataList.get(position).getBannerImage();
        Glide.with(mContext)
                .load(strUrl)
                .placeholder(R.drawable.default_banner_img)
                .into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
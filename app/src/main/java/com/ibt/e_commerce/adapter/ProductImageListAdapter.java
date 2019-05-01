package com.ibt.e_commerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.e_commerce.R;
import com.ibt.e_commerce.modal.category.ProductImageList;
import com.ibt.e_commerce.modal.category.ProductList;
import com.ibt.e_commerce.ui.activity.ProductDetailsActivity;

import java.util.List;

public class ProductImageListAdapter extends RecyclerView.Adapter<ProductImageListAdapter.ViewHolder> {

    private List<ProductImageList> productLists;
    private Context context;
    private View.OnClickListener clickListener;

    public ProductImageListAdapter(Context context, List<ProductImageList> productLists, View.OnClickListener listener) {
        this.productLists = productLists;
        this.context = context;
        this.clickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Glide.with(context)
                .load(productLists.get(position).getProductImage())
                .placeholder(R.drawable.ic_products)
                .into(holder.ivListProduct);

        holder.ivListProduct.setTag(position);
        holder.ivListProduct.setOnClickListener(clickListener);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivListProduct;

        public ViewHolder(View v) {
            super(v);
            ivListProduct = v.findViewById(R.id.ivListProduct);
        }
    }
}

package com.ibt.e_commerce.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ibt.e_commerce.R;
import com.ibt.e_commerce.modal.category.ProductImageList;

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
                .into(holder.imgProduct);

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

        private CardView ivListProduct;
        private ImageView imgProduct;

        public ViewHolder(View v) {
            super(v);
            imgProduct = v.findViewById(R.id.imgProduct);
            ivListProduct = v.findViewById(R.id.ivListProduct);
        }
    }
}

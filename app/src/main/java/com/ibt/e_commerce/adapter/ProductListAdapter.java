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
import com.ibt.e_commerce.modal.category.ProductList;
import com.ibt.e_commerce.ui.activity.ProductDetailsActivity;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<ProductList> productLists;
    private Context context;

    public ProductListAdapter(Context context, List<ProductList> productLists) {
        this.productLists = productLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txtProductName.setText(productLists.get(position).getProductType());
        holder.txtProductPrice.setText("Rs. " + productLists.get(position).getProductPrice());

        if (productLists.get(position).getProductImage().size() > 0) {
            Glide.with(context)
                    .load(productLists.get(position).getProductImage().get(0).getProductImage())
                    .placeholder(R.drawable.default_banner_img)
                    .into(holder.imgProduct);
        }

        holder.llProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("product_data", (Parcelable) productLists.get(position));
                context.startActivity(intent);
            }
        });
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

        private LinearLayout llProduct;
        private TextView txtProductName, txtProductPrice;
        private ImageView imgProduct;

        public ViewHolder(View v) {
            super(v);
            txtProductPrice = v.findViewById(R.id.txtProductPrice);
            txtProductName = v.findViewById(R.id.txtProductName);
            llProduct = v.findViewById(R.id.llProduct);
            imgProduct = v.findViewById(R.id.imgProduct);
        }
    }
}

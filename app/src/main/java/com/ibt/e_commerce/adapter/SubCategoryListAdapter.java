package com.ibt.e_commerce.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.e_commerce.R;
import com.ibt.e_commerce.custom_interface.ClickItemInterface;
import com.ibt.e_commerce.modal.category.Subcategory;

import java.util.List;

public class SubCategoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Subcategory> categoryLists;
    private Context context;
    private ClickItemInterface clickItemInterface;
    private int parentPosition;
    private int strFrom;

    public SubCategoryListAdapter(Context context, List<Subcategory> categoryLists, ClickItemInterface clickItemInterface,
                                  int parentPosition, int strFrom) {
        this.categoryLists = categoryLists;
        this.context = context;
        this.clickItemInterface = clickItemInterface;
        this.parentPosition = parentPosition;
        this.strFrom = strFrom;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (strFrom) {
            case 1:
                return new NavViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_list, parent, false));
            case 2:
                return new HomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sub_category_list, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (strFrom) {
            case 1:
                NavViewHolder navViewHolder =(NavViewHolder)holder;
                navViewHolder.txtCategory.setText(categoryLists.get(position).getSubCategoryName());
                navViewHolder.txtCategory.setTextColor(context.getResources().getColor(R.color.gray_b));

                Glide.with(context)
                        .load("")
                        .into(navViewHolder.imgCategory);

                navViewHolder.llCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickItemInterface.getProductDetail(parentPosition, position, categoryLists.get(position));
                    }
                });
                break;
            case 2:
                HomeViewHolder homeViewHolder =(HomeViewHolder)holder;
                homeViewHolder.txtCategory.setText(categoryLists.get(position).getSubCategoryName());
                homeViewHolder.txtCategory.setTextColor(context.getResources().getColor(R.color.gray_b));

                homeViewHolder.txtCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickItemInterface.getProductDetail(parentPosition, position, categoryLists.get(position));
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return categoryLists.size();
    }

    public static class NavViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout llCategory;
        private TextView txtCategory;
        private ImageView imgCategory;
        private RecyclerView recyclerViewSubCategory;

        public NavViewHolder(View v) {
            super(v);
            recyclerViewSubCategory = v.findViewById(R.id.recyclerViewSubCategory);
            txtCategory = v.findViewById(R.id.txtCategory);
            llCategory = v.findViewById(R.id.llCategory);
            imgCategory = v.findViewById(R.id.imgCategory);
        }
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCategory;

        public HomeViewHolder(View v) {
            super(v);
            txtCategory = v.findViewById(R.id.txtCategory);
        }
    }
}

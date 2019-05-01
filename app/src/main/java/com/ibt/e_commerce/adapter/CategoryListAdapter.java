package com.ibt.e_commerce.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.e_commerce.R;
import com.ibt.e_commerce.custom_interface.ClickItemInterface;
import com.ibt.e_commerce.modal.category.CategoryList;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CategoryList> categoryLists;
    private Context context;
    private View.OnClickListener onClickListener;
    private ClickItemInterface clickItemInterface;
    private int viewFrom;

    public CategoryListAdapter(Context context, List<CategoryList> categoryLists, View.OnClickListener onClickListener,
                               ClickItemInterface clickItemInterface, int viewFrom) {
        this.categoryLists = categoryLists;
        this.context = context;
        this.onClickListener = onClickListener;
        this.clickItemInterface = clickItemInterface;
        this.viewFrom = viewFrom;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewFrom) {
            case 1:
                return new NavViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_list, parent, false));
            case 2:
                return new HomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_list_b, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        switch (viewFrom) {
            case 1:
                NavViewHolder navViewHolder = (NavViewHolder) holder;
                navViewHolder.txtCategory.setText(categoryLists.get(position).getCategoryName());

                Glide.with(context)
                        .load(categoryLists.get(position).getCategoryImage())
                        .placeholder(R.drawable.ic_error)
                        .into(navViewHolder.imgCategory);

                navViewHolder.llCategory.setTag(position);
                navViewHolder.llCategory.setOnClickListener(onClickListener);

                SubCategoryListAdapter subCatAdapter = new SubCategoryListAdapter(context, categoryLists.get(position).getSubcategory(),
                        clickItemInterface, position, 1);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                navViewHolder.recyclerViewSubCategory.setLayoutManager(mLayoutManager);
                navViewHolder.recyclerViewSubCategory.setItemAnimator(new DefaultItemAnimator());
                navViewHolder.recyclerViewSubCategory.setAdapter(subCatAdapter);
                subCatAdapter.notifyDataSetChanged();
                break;
            case 2:
                HomeViewHolder homeViewHolder = (HomeViewHolder) holder;
                homeViewHolder.txtCategory.setText(categoryLists.get(position).getCategoryName());

                Glide.with(context)
                        .load(categoryLists.get(position).getCategoryImage())
                        .placeholder(R.drawable.ic_error)
                        .into(homeViewHolder.imgCategory);

                if (position == 0) {
                    homeViewHolder.llCategory.setBackground(context.getResources().getDrawable(R.drawable.layout_bg4));
                } else if (position == 1) {
                    homeViewHolder.llCategory.setBackground(context.getResources().getDrawable(R.drawable.layout_bg5));
                } else if (position == 2) {
                    homeViewHolder.llCategory.setBackground(context.getResources().getDrawable(R.drawable.layout_bg6));
                }
                homeViewHolder.llCategory.setTag(position);
                homeViewHolder.llCategory.setOnClickListener(onClickListener);
                homeViewHolder.btnView.setTag(position);
                homeViewHolder.btnView.setOnClickListener(onClickListener);

                SubCategoryListAdapter subCatAdapterB = new SubCategoryListAdapter(context, categoryLists.get(position).getSubcategory(),
                        clickItemInterface, position, 2);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
                RecyclerView.LayoutManager mLayoutManagerB = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                homeViewHolder.recyclerViewSubCategory.setLayoutManager(gridLayoutManager);
                homeViewHolder.recyclerViewSubCategory.setItemAnimator(new DefaultItemAnimator());
                homeViewHolder.recyclerViewSubCategory.setAdapter(subCatAdapterB);
                subCatAdapterB.notifyDataSetChanged();
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

        private LinearLayout llCategory;
        private TextView txtCategory;
        private ImageView imgCategory;
        private RecyclerView recyclerViewSubCategory;
        private Button btnView;

        public HomeViewHolder(View v) {
            super(v);
            recyclerViewSubCategory = v.findViewById(R.id.recyclerViewSubCategory);
            btnView = v.findViewById(R.id.btnView);
            txtCategory = v.findViewById(R.id.txtCategory);
            llCategory = v.findViewById(R.id.llCategory);
            imgCategory = v.findViewById(R.id.imgCategory);
        }
    }
}

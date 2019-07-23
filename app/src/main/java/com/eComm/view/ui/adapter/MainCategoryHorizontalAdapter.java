package com.eComm.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eComm.R;
import com.eComm.databinding.AdapterMainCategoryHorizontalBinding;
import com.eComm.view.ui.fragments.HomeFragment;

public class MainCategoryHorizontalAdapter extends RecyclerView.Adapter<MainCategoryHorizontalAdapter.MyViewHolder> {
    AdapterMainCategoryHorizontalBinding mBinding;

    public MainCategoryHorizontalAdapter(Context context) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.adapter_main_category_horizontal, viewGroup, false);
        return new MyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AdapterMainCategoryHorizontalBinding mBinding;

        public MyViewHolder(AdapterMainCategoryHorizontalBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}

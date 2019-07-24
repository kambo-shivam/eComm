package com.eComm.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eComm.R;
import com.eComm.databinding.AdapterDealOfTheDayBinding;

public class DealOfTheDayAdapter extends RecyclerView.Adapter<DealOfTheDayAdapter.MyViewHolder> {
    AdapterDealOfTheDayBinding mBinding;

    public DealOfTheDayAdapter(Context context) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.adapter_deal_of_the_day, viewGroup, false);
        return new MyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AdapterDealOfTheDayBinding mBinding;

        public MyViewHolder(@NonNull AdapterDealOfTheDayBinding itemView) {
            super(itemView.getRoot());
            this.mBinding = itemView;
        }
    }
}

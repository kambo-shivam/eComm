package com.eComm.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eComm.R;
import com.eComm.databinding.AdapterItemInCartHomeBinding;

public class ItemInCartAdapter extends RecyclerView.Adapter<ItemInCartAdapter.MyViewHolder> {
    AdapterItemInCartHomeBinding cartHomeBinding;

    public ItemInCartAdapter(Context context) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        cartHomeBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.adapter_item_in_cart_home, viewGroup, false);
        return new MyViewHolder(cartHomeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AdapterItemInCartHomeBinding cartHomeBinding;

        public MyViewHolder(@NonNull AdapterItemInCartHomeBinding itemView) {
            super(itemView.getRoot());
            this.cartHomeBinding = itemView;
        }
    }
}

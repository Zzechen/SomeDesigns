package com.zzc.somedesigns.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ItemMainBinding;

import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/14
 * @desc ${desc}
 */

class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    private List<Item> mItems;
    private Context mContext;
    private LayoutInflater mInflater;

    public ItemAdapter(List<Item> items, Context context) {
        mItems = items;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_main, parent, false);
        ItemHolder holder = new ItemHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final Item item = mItems.get(position);
        holder.binding.tvItemDesc.setText(item.getTitleId());
        holder.binding.tvItemDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, item.getClz()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        private final ItemMainBinding binding;

        public ItemHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

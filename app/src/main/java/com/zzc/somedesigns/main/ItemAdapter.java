package com.zzc.somedesigns.main;

import android.content.Context;
import android.content.Intent;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.base.BaseRecyclerViewAdapter;
import com.zzc.somedesigns.databinding.ItemMainBinding;

import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/14
 * @desc ${desc}
 */

class ItemAdapter extends BaseRecyclerViewAdapter<Item, ItemMainBinding> {

    ItemAdapter(Context context, List<Item> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemMainBinding> holder, int position) {
        final Item item = getDatas().get(position);
        holder.getBinding().tvItemDesc.setText(item.getTitleId());
        holder.getBinding().tvItemDesc.setOnClickListener(
                v -> getContext().startActivity(new Intent(getContext(), item.getClz())));
    }

    @Override
    protected int layoutId() {
        return R.layout.item_main;
    }
}

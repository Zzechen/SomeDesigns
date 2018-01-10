package com.zzc.somedesigns.loginandperson.person;

import android.content.Context;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.base.BaseRecyclerViewAdapter;
import com.zzc.somedesigns.databinding.ItemPayBinding;

import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/14
 * @desc ${desc}
 */

class PayAdapter extends BaseRecyclerViewAdapter<PayModel, ItemPayBinding> {

    PayAdapter(Context context, List<PayModel> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemPayBinding> holder, int position) {
        PayModel model = getDatas().get(position);
        ItemPayBinding binding = holder.getBinding();
        binding.tvItemPaySeller.setText(model.getSeller());
        binding.tvItemPayStatus.setText(StatusHelper.desc(model.getStatus()));
        binding.vItemStatus.setBackgroundResource(StatusHelper.drawable(model.getStatus()));
        binding.tvItemPayTime.setText(model.getTime());
        binding.tvItemPayPrice.setText(String.format("$%.2f", model.getPrice()));
        binding.tvItemPayDelete.setOnClickListener(v -> remove(position));
    }

    @Override
    protected int layoutId() {
        return R.layout.item_pay;
    }
}

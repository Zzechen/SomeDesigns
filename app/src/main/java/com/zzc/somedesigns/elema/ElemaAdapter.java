package com.zzc.somedesigns.elema;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.base.BaseRecyclerViewAdapter;
import com.zzc.somedesigns.databinding.ItemElemaBinding;

import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/27
 * @desc ${desc}
 */

public class ElemaAdapter extends BaseRecyclerViewAdapter<ElemaGoods, ItemElemaBinding> {

    public ElemaAdapter(Context context, List<ElemaGoods> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemElemaBinding> holder, int position) {
        ElemaGoods goods = getDatas().get(position);
        holder.getBinding().tvItemElemaPrice.setText(String.format("￥%.2f", goods.getPrice()));
        holder.getBinding().ivItemElemaImg.setImageResource(goods.getResId());
        holder.getBinding().tvItemElemaTitle.setText(goods.getTitle());
    }

    @Override
    protected int layoutId() {
        return R.layout.item_elema;
    }
}

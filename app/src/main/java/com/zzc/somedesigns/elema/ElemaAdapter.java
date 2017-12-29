package com.zzc.somedesigns.elema;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ItemElemaBinding;

import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/27
 * @desc ${desc}
 */

public class ElemaAdapter extends RecyclerView.Adapter<ElemaAdapter.Holder> {
    private List<ElemaGoods> mList;
    private LayoutInflater mInflater;

    public ElemaAdapter(Context context, List<ElemaGoods> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_elema, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ElemaGoods goods = mList.get(position);
        holder.binding.tvItemElemaPrice.setText(String.format("￥%.2f", goods.getPrice()));
        holder.binding.ivItemElemaImg.setImageResource(goods.getResId());
        holder.binding.tvItemElemaTitle.setText(goods.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        private ItemElemaBinding binding;

        public Holder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

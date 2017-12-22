package com.zzc.somedesigns.loginandperson.person;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ItemPayBinding;

import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/14
 * @desc ${desc}
 */

class PayAdapter extends RecyclerView.Adapter<PayAdapter.PayHolder> {
    private List<PayModel> mList;
    private LayoutInflater mInflater;

    public PayAdapter(List<PayModel> list, Context context) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public PayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_pay, parent, false);
        PayHolder holder = new PayHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(PayHolder holder, final int position) {
        PayModel model = mList.get(position);
        holder.binding.tvItemPaySeller.setText(model.getSeller());
        holder.binding.tvItemPayStatus.setText(StatusHelper.desc(model.getStatus()));
        holder.binding.vItemStatus.setBackgroundResource(StatusHelper.drawable(model.getStatus()));
        holder.binding.tvItemPayTime.setText(model.getTime());
        holder.binding.tvItemPayPrice.setText(String.format("$%.2f", model.getPrice()));
        holder.binding.tvItemPayDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class PayHolder extends RecyclerView.ViewHolder {
        private final ItemPayBinding binding;

        public PayHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

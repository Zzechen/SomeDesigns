package com.zzc.somedesigns.loginandperson.person;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ItemPersonPagerBinding;
import com.zzc.somedesigns.utils.SizeUtils;

import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/20
 * @desc ${desc}
 */

public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.Holder> {
    private List<PayModel> mList;
    private LayoutInflater mInflater;
    private Context mContext;

    public PagerAdapter(Context context, List<PayModel> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_person_pager, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        PayModel model = mList.get(position);
        holder.binding.tvItemPagerPrice.setText("$" + model.getPrice());
        holder.binding.tvItemPagerDesc.setText(StatusHelper.desc(model.getStatus()));
        int drawableId = StatusHelper.drawable(model.getStatus());
        Drawable drawable = mContext.getResources().getDrawable(drawableId);
        if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setCornerRadius(SizeUtils.dp2px(mContext, 5));
        }
        holder.binding.getRoot().setBackgroundDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private final ItemPersonPagerBinding binding;

        public Holder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}


package com.zzc.somedesigns.loginandperson.person;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.base.BaseRecyclerViewAdapter;
import com.zzc.somedesigns.databinding.ItemPersonPagerBinding;
import com.zzc.somedesigns.utils.SizeUtils;

import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/20
 * @desc ${desc}
 */

class PagerAdapter extends BaseRecyclerViewAdapter<PayModel, ItemPersonPagerBinding> {

    PagerAdapter(Context context, List<PayModel> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemPersonPagerBinding> holder, int position) {
        PayModel model = getDatas().get(position);
        holder.getBinding().tvItemPagerPrice.setText("$" + model.getPrice());
        holder.getBinding().tvItemPagerDesc.setText(StatusHelper.desc(model.getStatus()));
        int drawableId = StatusHelper.drawable(model.getStatus());
        Drawable drawable = getContext().getResources().getDrawable(drawableId);
        if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setCornerRadius(SizeUtils.dp2px(getContext(), 5));
        }
        holder.getBinding().getRoot().setBackgroundDrawable(drawable);
    }

    @Override
    protected int layoutId() {
        return R.layout.item_person_pager;
    }
}


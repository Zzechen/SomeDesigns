package com.zzc.somedesigns.weibo.discover;

import android.content.Context;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.base.BaseRecyclerViewAdapter;
import com.zzc.somedesigns.databinding.ItemWeiboDiscoverHeaderPagerBinding;

import java.util.List;

/**
 * auth zzc
 * date 2018/1/10
 * desc ${desc}
 */

class HeaderPagerAdater extends BaseRecyclerViewAdapter<Integer, ItemWeiboDiscoverHeaderPagerBinding> {
    HeaderPagerAdater(Context context, List<Integer> datas) {
        super(context, datas);
    }

    @Override
    protected int layoutId() {
        return R.layout.item_weibo_discover_header_pager;
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemWeiboDiscoverHeaderPagerBinding> holder, int position) {
        holder.getBinding().ivPic.setImageResource(getDatas().get(position));
    }
}

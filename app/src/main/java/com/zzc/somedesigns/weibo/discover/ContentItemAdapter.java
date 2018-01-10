package com.zzc.somedesigns.weibo.discover;

import android.content.Context;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.base.BaseRecyclerViewAdapter;
import com.zzc.somedesigns.databinding.ItemWeiboDiscoverContentBinding;

import java.util.List;

/**
 * auth zzc
 * date 2018/1/10
 * desc ${desc}
 */

class ContentItemAdapter extends BaseRecyclerViewAdapter<String, ItemWeiboDiscoverContentBinding> {
    ContentItemAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected int layoutId() {
        return R.layout.item_weibo_discover_content;
    }

    @Override
    public void onBindViewHolder(ViewHolder<ItemWeiboDiscoverContentBinding> holder, int position) {
        String content = getDatas().get(position);
        holder.getBinding().tvContent.setText(content);
    }
}

package com.zzc.somedesigns.zhihuad;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ItemAdDescBinding;
import com.zzc.somedesigns.databinding.ItemAdImgBinding;

import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/20
 * @desc ${desc}
 */

class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {
    private static final int TYPE_TEXT = 0;
    private static final int TYPE_IMG = 1;
    private List<ItemModel> mList;
    private LayoutInflater mInflater;

    public ItemAdapter(Context context, List<ItemModel> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(TYPE_IMG == viewType ? R.layout.item_ad_img : R.layout.item_ad_desc,
                parent, false);
        return new Holder(view, viewType);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ItemModel model = mList.get(position);
        holder.setBg(model.getResId());
        holder.setDesc(model.getDesc());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0 != mList.get(position).getResId() ? TYPE_IMG : TYPE_TEXT;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private ItemAdImgBinding mImgBinding;
        private ItemAdDescBinding mDescBinding;

        public Holder(View itemView, int type) {
            super(itemView);
            ViewDataBinding binding = DataBindingUtil.bind(itemView);
            if (TYPE_IMG == type) {
                mImgBinding = (ItemAdImgBinding) binding;
            } else {
                mDescBinding = (ItemAdDescBinding) binding;
            }
        }

        void setDesc(String desc) {
            if (mDescBinding != null) {
                mDescBinding.tvItemAdDesc.setText(desc);
            }
        }

        void setBg(int resId) {
            if (mImgBinding != null) {
                mImgBinding.ivItemAdImg.setImageResource(resId);
            }
        }
    }
}

package com.zzc.somedesigns.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * auth zzc
 * date 2018/1/10
 * desc ${desc}
 */

public abstract class BaseRecyclerViewAdapter<Data, Binding extends ViewDataBinding> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder<Binding>> {
    private List<Data> mDatas;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public BaseRecyclerViewAdapter(Context context, List<Data> datas) {
        mDatas = datas != null ? datas : new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public ViewHolder<Binding> onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(layoutId(), parent, false);
        return (ViewHolder<Binding>) new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    protected abstract @LayoutRes
    int layoutId();

    protected List<Data> getDatas() {
        return mDatas;
    }

    protected Context getContext() {
        return mContext;
    }

    public void addData(Data data) {
        mDatas.add(data);
        notifyItemInserted(mDatas.size() - 1);
    }

    public void addData(int index, Data data) {
        mDatas.add(index, data);
        notifyItemInserted(index);
    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(Data data) {
        int index = -1;
        int size = mDatas.size();
        for (int i = 0; i < size; i++) {
            if (mDatas.get(i).equals(data)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            mDatas.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void setData(int index, Data data) {
        mDatas.set(index, data);
        notifyItemChanged(index);
    }

    public void addDatas(List<Data> datas) {
        int size = mDatas.size();
        mDatas.addAll(datas);
        notifyItemRangeInserted(size - 1, datas.size());
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder<Binding extends ViewDataBinding> extends RecyclerView.ViewHolder {
        private Binding mBinding;

        ViewHolder(View itemView) {
            super(itemView);
        }

        public Binding getBinding() {
            if (mBinding == null) {
                mBinding = DataBindingUtil.bind(itemView);
            }
            return mBinding;
        }
    }
}

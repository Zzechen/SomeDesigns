package com.zzc.somedesigns.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created : zzc
 * Time : 2017/8/1
 * Email : zzc1259@163.com
 * Description : ${desc}
 */

public abstract class BasePagerAdapter<T> extends PagerAdapter {
    protected List<T> mList;
    protected Context mContext;
    private SparseArray<View> mViewList;
    private int layoutId;
    private PagerItemClickListener<T> mItemClickListener;

    public BasePagerAdapter(List<T> list, int layoutId, Context context) {
        mList = list;
        this.layoutId = layoutId;
        mViewList = new SparseArray<>();
        mContext = context;
    }

    public void setItemClickListener(PagerItemClickListener<T> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = mViewList.get(position);
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(layoutId, container, false);
            mViewList.put(position, view);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        container.addView(view);
        final T model = mList.get(position);
        bind(view, model, position);
        if (mItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.itemClick(model, position);
                }
            });
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public abstract void bind(View view, T model, int position);
}
package com.zzc.somedesigns.zhihuad;

import android.support.annotation.DrawableRes;

/**
 * @auth zzc
 * @date 2017/12/20
 * @desc ${desc}
 */

class ItemModel {
    @DrawableRes
    private int resId;
    private String desc;

    public ItemModel(int resId, String desc) {
        this.resId = resId;
        this.desc = desc;
    }

    public int getResId() {
        return resId;
    }

    public String getDesc() {
        return desc;
    }
}

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
    private int type;

    public ItemModel(int resId, String desc,int type) {
        this.resId = resId;
        this.desc = desc;
        this.type = type;
    }

    public int getResId() {
        return resId;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }
}

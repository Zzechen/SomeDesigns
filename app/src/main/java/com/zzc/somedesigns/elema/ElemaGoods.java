package com.zzc.somedesigns.elema;

/**
 * @auth zzc
 * @date 2017/12/27
 * @desc ${desc}
 */

public class ElemaGoods {
    private int resId;
    private String title;
    private float price;

    public ElemaGoods(int resId, String title, float price) {
        this.resId = resId;
        this.title = title;
        this.price = price;
    }

    public int getResId() {
        return resId;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }
}

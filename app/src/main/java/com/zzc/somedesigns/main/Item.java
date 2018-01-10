package com.zzc.somedesigns.main;

import android.support.v7.app.AppCompatActivity;

/**
 * @auth zzc
 * @date 2017/12/14
 * @desc ${desc}
 */

class Item {
    private int titleId;
    private Class<? extends AppCompatActivity> clz;

    Item(int titleId, Class<? extends AppCompatActivity> clz) {
        this.titleId = titleId;
        this.clz = clz;
    }

    int getTitleId() {
        return titleId;
    }

    Class<? extends AppCompatActivity> getClz() {
        return clz;
    }
}

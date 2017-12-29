package com.zzc.somedesigns.elema.goodsa;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zzc.somedesigns.R;

/**
 * @auth zzc
 * @date 2017/12/26
 * @desc ${使用CoordinatorLayout实现,其实两种原理相同，只是代码不同}
 */

public class ElemaGoodsAActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_elema_a);
    }
}

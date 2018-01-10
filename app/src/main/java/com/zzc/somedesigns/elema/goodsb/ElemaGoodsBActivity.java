package com.zzc.somedesigns.elema.goodsb;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ActivityElemaBBinding;
import com.zzc.somedesigns.elema.ElemaAdapter;
import com.zzc.somedesigns.elema.ElemaGoods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @auth zzc
 * @date 2017/12/26
 * @desc ${使用NestedScrollingParent实现，简版，并没有全部实现}
 */

public class ElemaGoodsBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityElemaBBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_elema_b);
        List<ElemaGoods> list = new ArrayList<>();
        binding.rvElema.setAdapter(new ElemaAdapter(this, list));
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            ElemaGoods goods = new ElemaGoods(R.drawable.img_zhihu_ad_1, "Title:" + i, r.nextFloat() * 100);
            list.add(goods);
        }
        binding.rvElema.setLayoutManager(new GridLayoutManager(this, 2));
        binding.ivElemaShare.setOnClickListener(toast);
        binding.ivElemaBarShare.setOnClickListener(toast);
        binding.ivElemaClose.setOnClickListener(v -> finish());

    }

    private Toast mToast;
    private View.OnClickListener toast = v -> {
        if (mToast != null) {
            mToast.cancel();
        } else {
            mToast = Toast.makeText(ElemaGoodsBActivity.this, "share", Toast.LENGTH_SHORT);
        }
        mToast.show();
    };
}

package com.zzc.somedesigns.zhihuad;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ActivityZhihuAdBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/20
 * @desc ${desc}
 */

public class ZhihuAdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityZhihuAdBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_zhihu_ad);
        List<ItemModel> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int resId = 0;
            int type = ItemAdapter.TYPE_TEXT;
            if (10 == i) {
                resId = R.drawable.img_zhihu_ad_1;
                type = ItemAdapter.TYPE_IMG;
            } else if (13 == i) {
                resId = R.drawable.img_zhihu_ad_2;
                type = ItemAdapter.TYPE_IMG_B;
            }
            list.add(new ItemModel(resId, "item" + i, type));
        }
        binding.rvZhifuAd.setLayoutManager(new LinearLayoutManager(this));
        binding.rvZhifuAd.setAdapter(new ItemAdapter(this, list));
    }
}

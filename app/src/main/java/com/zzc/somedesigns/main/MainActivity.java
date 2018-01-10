package com.zzc.somedesigns.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ActivityMainBinding;
import com.zzc.somedesigns.demo.DemoActivity;
import com.zzc.somedesigns.elema.goodsb.ElemaGoodsBActivity;
import com.zzc.somedesigns.loginandperson.login.LoginActivity;
import com.zzc.somedesigns.weibo.discover.WeiboDiscoverActivity;
import com.zzc.somedesigns.zhihuad.ZhihuAdActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/13
 * @desc ${desc}
 */

public class MainActivity extends AppCompatActivity {
    private Item[] mItems = {
            new Item(R.string.activity_login, LoginActivity.class),
            new Item(R.string.activity_zhihu_ad, ZhihuAdActivity.class),
            new Item(R.string.elema_goods_b, ElemaGoodsBActivity.class),
            new Item(R.string.weibo_discover, WeiboDiscoverActivity.class),
            new Item(R.string.demo, DemoActivity.class),
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        List<Item> items = Arrays.asList(mItems);
        binding.rvMain.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decor = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        decor.setDrawable(getResources().getDrawable(R.drawable.divider_horizontal));
        binding.rvMain.addItemDecoration(decor);
        binding.rvMain.setAdapter(new ItemAdapter(items, this));
    }
}

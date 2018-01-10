package com.zzc.somedesigns.weibo.discover;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ActivityWeiboDicoverBinding;
import com.zzc.somedesigns.databinding.LayoutWeiboDiscoverHeaderBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * auth zzc
 * date 2018/1/10
 * desc ${desc}
 */

public class WeiboDiscoverActivity extends AppCompatActivity {
    private static final String TAG = WeiboDiscoverActivity.class.toString();

    private String[] titles = new String[]{
            "title1", "title2", "title3", "title4", "title5"
    };
    private LayoutWeiboDiscoverHeaderBinding mHeaderBinding;
    private int mDownScrollRange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWeiboDicoverBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weibo_dicover);
        mHeaderBinding = binding.header;
        ArrayList<Pair<String, Fragment>> fragments = new ArrayList<>();
        TabLayout tbTitle = mHeaderBinding.tbTitle;
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new Pair<String, Fragment>(titles[i], WeiboItemsFragment.newInstance(i)));
        }
        binding.vpWeiboDiscover.setAdapter(new ContentPagerAdapter(getSupportFragmentManager(), fragments));
        tbTitle.setupWithViewPager(binding.vpWeiboDiscover, false);
        mHeaderBinding.tbl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                boolean isTop = Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange();
                mHeaderBinding.ivRenew.setVisibility(isTop ? View.VISIBLE : View.INVISIBLE);
                setDownable(appBarLayout, !isTop);
            }
        });
        mHeaderBinding.ivRenew.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setVisibility(View.INVISIBLE);
                        setDownable(mHeaderBinding.tbl, true);
                        mHeaderBinding.tbl.setExpanded(true);
                    }
                }
        );

        ArrayList<Integer> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(R.mipmap.ic_launcher_round);
        }
        mHeaderBinding.rvPager.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mHeaderBinding.rvPager.setAdapter(new HeaderPagerAdater(this, datas));
    }

    private void setDownable(AppBarLayout appBarLayout, boolean downable) {
        try {
            Field mDownPreScrollRangeFiled = appBarLayout.getClass().getDeclaredField("mDownScrollRange");
            mDownPreScrollRangeFiled.setAccessible(true);
            if (mDownScrollRange == 0) {
                mDownScrollRange = mDownPreScrollRangeFiled.getInt(appBarLayout);
            }
            Log.e(TAG, String.format("setDownable,downable:%b,mDownScrollRange:%d¬", downable, mDownScrollRange));
            mDownPreScrollRangeFiled.setInt(appBarLayout, downable ? mDownScrollRange : 0);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

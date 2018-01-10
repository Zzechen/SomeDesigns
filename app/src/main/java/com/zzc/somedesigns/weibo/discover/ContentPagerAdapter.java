package com.zzc.somedesigns.weibo.discover;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * auth zzc
 * date 2018/1/10
 * desc ${desc}
 */

class ContentPagerAdapter extends FragmentPagerAdapter {
    private List<Pair<String, Fragment>> mFragments;

     ContentPagerAdapter(FragmentManager fm, List<Pair<String, Fragment>> fragments) {
        super(fm);
        mFragments = fragments != null ? fragments : new ArrayList<Pair<String, Fragment>>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position).second;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).first;
    }
}

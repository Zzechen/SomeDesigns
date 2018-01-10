package com.zzc.somedesigns.weibo.discover;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.FragmentWeiboItemsBinding;

import java.util.ArrayList;

/**
 * auth zzc
 * date 2018/1/10
 * desc ${desc}
 */

public class WeiboItemsFragment extends Fragment {
    private static final String KEY = "WeiboItemsFragment_Page";
    private static final int COUNT = 100;
    private int mPage;
    private ContentItemAdapter mAdapter;
    private int added;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPage = getArguments().getInt(KEY);
        return inflater.inflate(R.layout.fragment_weibo_items, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final FragmentWeiboItemsBinding binding = DataBindingUtil.bind(view);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            datas.add(String.format("content %d for page %d", i + 1, mPage));
        }
        mAdapter = new ContentItemAdapter(getContext(), datas);
        binding.rvWeiboItems.setAdapter(mAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvWeiboItems.setLayoutManager(layoutManager);
        binding.sfl.setOnRefreshListener(() ->
                binding.sfl.postDelayed(() -> {
                    mAdapter.addData(0, "Added" + added++);
                    layoutManager.scrollToPosition(0);
                    binding.sfl.setRefreshing(false);
                }, 1000));
    }

    public static WeiboItemsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(KEY, page);
        WeiboItemsFragment fragment = new WeiboItemsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

package com.zzc.somedesigns.loginandperson.person;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ActivityPersonBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auth zzc
 * @date 2017/12/14
 * @desc ${desc}
 */

public class PersonActivity extends AppCompatActivity {
    private static final String EMAIL = "email";
    private static final String PSD = "psd";
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityPersonBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_person);
        String email = getIntent().getStringExtra(EMAIL);
        String psd = getIntent().getStringExtra(PSD);
        final List<PayModel> list = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            PayModel model = new PayModel();
            model.setSeller("Seller" + i);
            model.setTime(new Date().toString());
            model.setStatus(i % 3 + 1);
            model.setPrice(i * 12.3f);
            list.add(model);
        }
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.include.rvPerson.setLayoutManager(layoutManager);
        final PayAdapter adapter = new PayAdapter(list, this);
        binding.include.rvPerson.setAdapter(adapter);
        binding.ivPersonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        List<PayModel> pagerList = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            PayModel model = new PayModel();
            model.setSeller("Seller" + i);
            model.setTime(new Date().toString());
            model.setStatus(i % 3 + 1);
            model.setPrice(i * 12.3f);
            pagerList.add(model);
        }
        binding.rvPersonPager.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvPersonPager.setAdapter(new PagerAdapter(this, pagerList));


        binding.ivPersonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.include.srlPerson.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int index = list.size() + 1;
                        PayModel model = new PayModel();
                        model.setSeller("Seller" + index);
                        model.setTime(new Date().toString());
                        model.setStatus(index % 3 + 1);
                        model.setPrice(index * 12.3f);
                        list.add(0, model);
                        adapter.notifyItemInserted(0);
                        binding.include.srlPerson.setRefreshing(false);
                        layoutManager.scrollToPosition(0);
                    }
                }, 200);
            }
        });
    }

    public static void start(Context context, String email, String psd) {
        Intent intent = new Intent(context, PersonActivity.class);
        intent.putExtra(EMAIL, email);
        intent.putExtra(PSD, psd);
        context.startActivity(intent);
    }
}

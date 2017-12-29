package com.zzc.somedesigns.demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ActivityDemoBinding;

/**
 * @auth zzc
 * @date 2017/12/26
 * @desc ${desc}
 */

public class DemoActivity extends AppCompatActivity {
    private static final String TAG = DemoActivity.class.toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDemoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_demo);
    }
}

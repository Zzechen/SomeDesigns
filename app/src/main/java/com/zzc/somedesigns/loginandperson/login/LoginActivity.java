package com.zzc.somedesigns.loginandperson.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.ActivityLoginBinding;

/**
 * @auth zzc
 * @date 2017/12/13
 * @desc ${desc}
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.btnLogin.setOnClickListener(v -> {
            LoginDialog dialog = LoginDialog.newInstance(true);
            dialog.show(getSupportFragmentManager());
        });
        binding.btnRegister.setOnClickListener(v -> {
            LoginDialog dialog = LoginDialog.newInstance(false);
            dialog.show(getSupportFragmentManager());
        });
    }
}

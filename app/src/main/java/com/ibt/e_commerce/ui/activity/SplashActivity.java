package com.ibt.e_commerce.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ibt.e_commerce.R;
import com.ibt.e_commerce.constant.Constant;
import com.ibt.e_commerce.utils.AppPreference;
import com.ibt.e_commerce.utils.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!AppPreference.getBooleanPreference(mContext, Constant.IsFirstTime)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                } else {
                    startActivity(new Intent(mContext, HomeActivity.class));
                }
                finish();
            }
        }, 3000);
    }
}

package com.ibt.e_commerce.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ibt.e_commerce.R;
import com.ibt.e_commerce.utils.BaseActivity;

public class OtpActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        init();
    }

    private void init() {
        findViewById(R.id.btnVerify).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVerify:
                startActivity(new Intent(mContext, HomeActivity.class));
                break;
        }
    }
}

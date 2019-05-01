package com.ibt.e_commerce.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.ibt.e_commerce.R;
import com.ibt.e_commerce.constant.Constant;
import com.ibt.e_commerce.utils.AppPreference;
import com.ibt.e_commerce.utils.AppProgressDialog;
import com.ibt.e_commerce.utils.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        dialog = new Dialog(mContext);
        findViewById(R.id.btnLogin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                AppPreference.setBooleanPreference(mContext, Constant.IsFirstTime, true);
                AppProgressDialog.show(dialog);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AppProgressDialog.hide(dialog);
                        startActivity(new Intent(mContext, HomeActivity.class));
                        finish();
                    }
                }, 1000);
                break;
        }
    }
}

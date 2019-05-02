package com.ibt.e_commerce.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ibt.e_commerce.R;
import com.ibt.e_commerce.retrofit_provider.RetrofitService;
import com.ibt.e_commerce.retrofit_provider.WebResponse;
import com.ibt.e_commerce.utils.Alerts;
import com.ibt.e_commerce.utils.BaseActivity;
import com.ibt.e_commerce.utils.EmailValidate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class ContactUsActivity extends BaseActivity implements View.OnClickListener {

    private String proName, proPrice, proDesc, proImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        init();
    }

    private void init() {
        findViewById(R.id.imgBack).setOnClickListener(this);
        findViewById(R.id.btnSubmit).setOnClickListener(this);
        proName = getIntent().getStringExtra("pro_name");
        proPrice = getIntent().getStringExtra("pro_price");
        proDesc = getIntent().getStringExtra("pro_desc");
        proImage = getIntent().getStringExtra("pro_image");

        ((EditText) findViewById(R.id.edtProductName)).setText(proName);
        ((EditText) findViewById(R.id.edtProductPrice)).setText(proPrice);
        ((EditText) findViewById(R.id.edtProductDescription)).setText(proDesc);

        Glide.with(mContext)
                .load(proImage)
                .placeholder(R.drawable.ic_products)
                .into((ImageView) findViewById(R.id.imgProduct));

    }

    private void enquiryApi() {
        String userName = ((EditText) findViewById(R.id.edtName)).getText().toString();
        String userEmail = ((EditText) findViewById(R.id.edtEmail)).getText().toString();
        String userPhone = ((EditText) findViewById(R.id.edtMobile)).getText().toString();

        if (userName.isEmpty()) {
            Alerts.show(mContext, "Enter user name...!!!");
        } else if (userEmail.isEmpty()) {
            Alerts.show(mContext, "Enter email id...!!!");
        } else if (!EmailValidate.isValidEmailId(userEmail)) {
            Alerts.show(mContext, "Enter valid email id...!!!");
        } else if (userPhone.isEmpty()) {
            Alerts.show(mContext, "Enter contact no...!!!");
        } else if (userPhone.length() < 10) {
            Alerts.show(mContext, "Enter valid contact no...!!!");
        } else {
            if (cd.isNetworkAvailable()) {
                RetrofitService.getResponseData(new Dialog(mContext), retrofitApiClient.enquiryApi(
                        userName, userEmail, userPhone, proName, proPrice, proDesc), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                startActivity(new Intent(mContext, ThankYouActivity.class));
                            } else {
                                Alerts.show(mContext, jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(mContext, error);
                    }
                });
            } else {
                cd.show(mContext);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                enquiryApi();
                break;
            case R.id.imgBack:
                finish();
                break;
        }
    }
}

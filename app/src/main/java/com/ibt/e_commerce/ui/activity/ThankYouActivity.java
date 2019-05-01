package com.ibt.e_commerce.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ibt.e_commerce.R;
import com.ibt.e_commerce.utils.BaseActivity;

public class ThankYouActivity extends BaseActivity {

    private String str = "https://lh3.googleusercontent.com/-QivllSlDWjg/XMmmulYM6mI/AAAAAAAAAJQ/vSvp0E83HEQAXJIIXaUmcuaoEd78zKLeACK8BGAs/s500/2019-05-01.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        ImageView img = findViewById(R.id.imgRocket);

        findViewById(R.id.txtContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        Glide.with(this)
                .load(str)
                .into(img);

    }

}

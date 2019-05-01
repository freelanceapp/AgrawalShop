package com.ibt.e_commerce.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ibt.e_commerce.R;
import com.ibt.e_commerce.utils.BaseActivity;

public class ThankYouActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        ImageView img = findViewById(R.id.imgRocket);

        Glide.with(this)
                .load(R.drawable.rocket_gif)
                .placeholder(R.drawable.ic_products)
                .into(img);

    }

}

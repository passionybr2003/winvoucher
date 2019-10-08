package com.example.winvoucher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import in.myinnos.androidscratchcard.ScratchCard;
/*
https://github.com/myinnos/AndroidScratchCard [WORKED]
https://github.com/sharish/ScratchView

 */

public class ShowCouponActivity extends AppCompatActivity {
    private ScratchCard mScratchCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_coupon);

        mScratchCard = findViewById(R.id.scratchCard);
        mScratchCard.setOnScratchListener(new ScratchCard.OnScratchListener() {
            @Override
            public void onScratch(ScratchCard scratchCard, float visiblePercent) {
                Intent intent = getIntent();
                String couponVal = intent.getStringExtra(DisplayVideoActivity.COUPON_VALUE);
                TextView textView = findViewById(R.id.couponValueTextView);
                textView.setText("You earned "+ couponVal);

                if (visiblePercent > 0.3) {
                    mScratchCard.setVisibility(View.GONE);
                    Toast.makeText(ShowCouponActivity.this, "Content Visible", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

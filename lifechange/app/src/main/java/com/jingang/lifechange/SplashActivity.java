package com.jingang.lifechange;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jingang.lifechange.base.BaseActivity;

/**
 * @author lijingang02
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(intent);
        finish();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },1000);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
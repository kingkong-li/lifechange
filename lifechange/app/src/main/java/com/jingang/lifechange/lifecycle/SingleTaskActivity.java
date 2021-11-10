package com.jingang.lifechange.lifecycle;

import android.os.Bundle;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

public class SingleTaskActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
    }
}
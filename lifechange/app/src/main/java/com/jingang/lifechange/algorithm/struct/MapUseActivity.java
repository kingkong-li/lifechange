package com.jingang.lifechange.algorithm.struct;

import android.os.Bundle;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

/**
 * @author jingang
 */
public class MapUseActivity extends BaseActivity {
    private static final String TAG=MapUseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_use);
        TwoSum.testTwoSum();

    }
}
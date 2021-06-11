package com.jingang.lifechange.algorithm.struct.array;

import android.os.Bundle;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

/**
 * @Description:  数组测试页面
 * @Author: kingkong.Li
 */
public class ArrayTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrary_test);
        new BaseArrayOption().checkBaseOption();
        new ArrayMonotonicity();
    }
}
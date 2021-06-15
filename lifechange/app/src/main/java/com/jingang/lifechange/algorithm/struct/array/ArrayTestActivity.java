package com.jingang.lifechange.algorithm.struct.array;

import android.os.Bundle;
import android.util.Log;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

/**
 * @Description:  数组测试页面
 * @Author: kingkong.Li
 */
public class ArrayTestActivity extends BaseActivity {

    private static final String TAG =ArrayTestActivity.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrary_test);
        new BaseArrayOption().checkBaseOption();
        int[] array=new int[]{
                1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3
        };
        Log.v(TAG,"Array="+BaseArrayOption.getIntArrayString(array));
        Log.v(TAG,"Array="+BaseArrayOption.getIntArrayString(
                new ArrayMonotonicity().findPeakInArray(array)));
        Log.v(TAG,"use method 2 Array="+BaseArrayOption.getIntArrayString(
                new ArrayMonotonicity().findPeakInArray2(array)));

    }
}
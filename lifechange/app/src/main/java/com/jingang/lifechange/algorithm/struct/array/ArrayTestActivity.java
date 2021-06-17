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
                1, 2, 3, 100, 4, 0, 10, 6, 5, -1, -3, 2, 3
        };
        Log.v(TAG,"Array="+BaseArrayOption.getIntArrayString(array));
        Log.v(TAG,"use method 1 Array="+BaseArrayOption.getIntArrayString(
                new ArrayMonotonicity().findPeakInArray1(array)));
        Log.v(TAG,"use method 2 Array="+BaseArrayOption.getIntArrayString(
                new ArrayMonotonicity().findPeakInArray2(array)));
        Log.v(TAG,"use method 3 Array="+BaseArrayOption.getIntArrayString(
                new ArrayMonotonicity().findPeakInArray3(array)));

        int[] array1=new int[]{-1, 5, 10, 20, 28, 3};
        int[] array2=new int[]{26, 134, 135, 15, 17};
        Log.v(TAG,"use method 1 getMin="+BaseArrayOption.getIntArrayString(
                new TwoArrayDifference().getMinDifference0(array1,array2)));
        Log.v(TAG,"use method 2 getMin="+BaseArrayOption.getIntArrayString(
                new TwoArrayDifference().getMinDifference1(array1,array2)));


    }
}
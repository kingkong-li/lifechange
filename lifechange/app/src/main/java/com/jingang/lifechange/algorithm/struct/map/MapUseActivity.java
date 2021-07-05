package com.jingang.lifechange.algorithm.struct.map;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jingang.lifechange.R;
import com.jingang.lifechange.algorithm.struct.TwoSum;
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
        String[] strings=new String[]{"cat","car","city","home","dog","tree"};
        boolean contain=new StringFind().contain(strings,"tre");
        Log.v(TAG," \"cat\",\"car\",\"city\",\"home\",\"dog\",\"tree\""+"  contain "+"tre="+
                contain);

    }


}
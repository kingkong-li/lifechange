package com.jingang.lifechange.struct;

import android.os.Bundle;
import android.util.Log;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jingang
 */
public class MapUseActivity extends BaseActivity {
    private static final String TAG=MapUseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_use);
        Map<Integer,Integer> map=new HashMap<>();
        map.put(1,2);
        map.put(1,3);
        Log.d(TAG, "map get(1)="+map.get(1));
    }
}
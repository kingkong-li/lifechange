package com.jingang.lifechange.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.jingang.lifechange.R;

/**
 * 这个页面主要是测试内存工具
 * @author jingang
 */
public class TestMemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_memory);
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int  totalMemory= (int) (Runtime.getRuntime().totalMemory() / 1024);
        int freeMemory=(int)(Runtime.getRuntime().freeMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB");
        Log.d("TAG", "Total memory is " + totalMemory + "KB");
        Log.d("TAG", "freeMemory memory is " + freeMemory + "KB");
    }
}
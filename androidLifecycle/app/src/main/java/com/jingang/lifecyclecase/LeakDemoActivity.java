package com.jingang.lifecyclecase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.jingang.lifecyclecase.base.BaseActivity;

import java.util.ArrayList;

/**
 * @author jingang
 */
public class LeakDemoActivity extends BaseActivity {
    public final static ArrayList<Context> mContextList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_demo);
        mContextList.add(this);
    }
}

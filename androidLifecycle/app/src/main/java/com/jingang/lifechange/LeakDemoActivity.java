package com.jingang.lifechange;

import android.content.Context;
import android.os.Bundle;

import com.jingang.lifechange.base.BaseActivity;

import java.util.ArrayList;

/**
 * 这个页面是主动制造内存泄露
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

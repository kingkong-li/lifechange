package com.jingang.lifechange;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.jingang.lifechange.algorithm.AlgorithmMainActivity;
import com.jingang.lifechange.algorithm.struct.array.ArrayTestActivity;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.generics.TestGenericsActivity;
import com.jingang.lifechange.lifecycle.LifeCycleMainActivity;
import com.jingang.lifechange.lifecycle.StandardActivity;
import com.jingang.lifechange.memory.LeakDemoActivity;
import com.jingang.lifechange.ui.LearnDisplayActivity;
import com.jingang.lifechange.utils.LogUtil;

/**
 * @author jingang
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Debug.startMethodTracing("jg");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button1=findViewById(R.id.bt_go_leaked_activity);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, LeakDemoActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Button button2=findViewById(R.id.bt_know_lifecycle);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, LifeCycleMainActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Button button3=findViewById(R.id.bt_learn_algorithm);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, AlgorithmMainActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Button button4=findViewById(R.id.bt_learn_list);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();

                intent.setClass(MainActivity.this, ArrayTestActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Button button5=findViewById(R.id.bt_learn_generics);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, TestGenericsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Button button6=findViewById(R.id.bt_learn_ui);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, LearnDisplayActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Debug.stopMethodTracing();

//        try {
//            int i=0;
////           Thread.currentThread().interrupt();
//        }catch (Exception e){
//            Log.v("JG","catch outer");
//        } finally {
//            Log.v("JG","finally outer");
//        }


    }

    @Override
    protected void onResume() {
        int i=0;
        i++;
        super.onResume();
    }
}

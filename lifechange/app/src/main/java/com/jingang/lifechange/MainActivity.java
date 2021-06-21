package com.jingang.lifechange;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jingang.lifechange.algorithm.AlgorithmMainActivity;
import com.jingang.lifechange.algorithm.struct.array.ArrayTestActivity;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.generics.TestGenericsActivity;
import com.jingang.lifechange.lifecycle.LifeCycleMainActivity;
import com.jingang.lifechange.lifecycle.StandardActivity;
import com.jingang.lifechange.memory.LeakDemoActivity;

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
        Debug.stopMethodTracing();

        try {
            String bb="你好";
            String aa=null;
            Log.v("aa","aa.length="+aa.length());
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        int i=0;
        i++;
        super.onResume();
    }
}

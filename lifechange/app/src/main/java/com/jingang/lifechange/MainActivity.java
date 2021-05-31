package com.jingang.lifechange;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;

import com.jingang.lifechange.algorithm.AlgorithmMainActivity;
import com.jingang.lifechange.algorithm.SortTestActivity;
import com.jingang.lifechange.algorithm.struct.listoperation.TestListOperationActivity;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.memory.LeakDemoActivity;
import com.jingang.lifechange.algorithm.struct.MapUseActivity;
import com.jingang.lifechange.ui.TestDialogLifecycleActivity;

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
                intent.setClass(MainActivity.this, TestDialogLifecycleActivity.class);
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
        Button button5=findViewById(R.id.bt_learn_list);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, TestListOperationActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Debug.stopMethodTracing();


    }



}

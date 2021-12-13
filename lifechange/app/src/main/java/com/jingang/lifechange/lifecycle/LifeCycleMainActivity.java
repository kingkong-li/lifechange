package com.jingang.lifechange.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

/**
 * @author lijingang02
 */
public class LifeCycleMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_main);
        Button button1=findViewById(R.id.bt_dialog_and_activity);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LifeCycleMainActivity.this, DialogWithActivity.class);
                LifeCycleMainActivity.this.startActivity(intent);
            }
        });
        Button button2=findViewById(R.id.bt_standard_start);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LifeCycleMainActivity.this, StandardActivity.class);
                LifeCycleMainActivity.this.startActivity(intent);
            }
        });
        Button button3=findViewById(R.id.bt_single_instance_activity);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LifeCycleMainActivity.this, SingleInstanceActivity.class);
                LifeCycleMainActivity.this.startActivity(intent);
            }
        });
        Button button4=findViewById(R.id.bt_single_top_activity);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LifeCycleMainActivity.this, StandardActivity.class);
                LifeCycleMainActivity.this.startActivity(intent);
            }
        });
        Button button5=findViewById(R.id.bt_single_task_activity);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LifeCycleMainActivity.this, SingleTaskActivity.class);
                LifeCycleMainActivity.this.startActivity(intent);
            }
        });
        Button button6=findViewById(R.id.bt_persist_activity);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LifeCycleMainActivity.this, PersistActivity.class);
                LifeCycleMainActivity.this.startActivity(intent);
            }
        });
        Button button7=findViewById(R.id.bt_fragment_activity);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LifeCycleMainActivity.this, FragmentContainerActivity.class);
                LifeCycleMainActivity.this.startActivity(intent);
            }
        });
    }
}
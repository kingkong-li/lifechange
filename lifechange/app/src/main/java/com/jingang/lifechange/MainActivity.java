package com.jingang.lifechange;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;

import com.jingang.lifechange.algorithm.SortTestActivity;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.base.PublicThreadPools;
import com.jingang.lifechange.memory.LeakDemoActivity;
import com.jingang.lifechange.struct.MapUseActivity;
import com.jingang.lifechange.ui.TestDialogLifecycleActivity;

import leakcanary.AppWatcher;

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
                intent.setClass(MainActivity.this, SortTestActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Button button4=findViewById(R.id.bt_learn_struct);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, MapUseActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


        Intent intent= new Intent();
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
//        bundle.putBinder("key",new Binder());
//        ImageView imageView=new ImageView(this);
//        imageView.setLayoutParams();
//        imageView.setScaleType();
//        imageView.setImageBitmap();
//        this.startActivity(intent);



        AppWatcher.INSTANCE.getObjectWatcher().watch(intent,"intent");

        PublicThreadPools.getService().submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        PublicThreadPools.getService().execute(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName(" my TaskName");
            }
        });


        Debug.stopMethodTracing();


    }


}

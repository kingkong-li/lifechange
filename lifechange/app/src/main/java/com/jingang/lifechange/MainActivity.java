package com.jingang.lifechange;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jingang.lifechange.aidl.AidlClientActivity;
import com.jingang.lifechange.algorithm.AlgorithmMainActivity;
import com.jingang.lifechange.algorithm.struct.array.ArrayTestActivity;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.blue.tooth.BlueDeviceListActivity;
import com.jingang.lifechange.generics.TestGenericsActivity;
import com.jingang.lifechange.lifecycle.LifeCycleMainActivity;
import com.jingang.lifechange.location.LocationMainActivity;
import com.jingang.lifechange.memory.LeakDemoActivity;
import com.jingang.lifechange.thread.OrderMethodClass;
import com.jingang.lifechange.ui.LearnDisplayActivity;
import com.jingang.lifechange.ui.gesture.GestureShowActivity;
import com.jingang.lifechange.utils.LiveDataBus;

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
                intent.setClass(MainActivity.this, GestureShowActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Button button7=findViewById(R.id.bt_learn_location);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, LocationMainActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Button button8=findViewById(R.id.bt_learn_aidl);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, AidlClientActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        Button button9=findViewById(R.id.bt_learn_blue_tooth);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, BlueDeviceListActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        LiveDataBus.TEST_DATA_FLOW.postValue(7);
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
        final OrderMethodClass orderMethodClass=new OrderMethodClass();
        for(int j=2;j<5;j++){
            final int finalJ = j;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(finalJ%3==0){
                        orderMethodClass.method1();
                    }else if (finalJ%3==1){
                        orderMethodClass.method2();
                    }else {
                        orderMethodClass.method3();
                    }

                }
            }).start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        long i=0;
//        while(true){
//            i=Math.round(Math.random()*10000000);
//            if(i==1){
//                Log.i(getTag(),"finally onStop end");
//                break;
//
//            }
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}

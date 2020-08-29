package com.jingang.lifecyclecase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.TraceCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jingang.lifecyclecase.base.BaseActivity;
import com.jingang.lifecyclecase.base.PublicThreadPools;
import com.jingang.lifecyclecase.utils.ActivityCache;
import com.jingang.lifecyclecase.utils.BubbleSort;
import com.jingang.lifecyclecase.utils.QuickSort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jingang
 */
public class MainActivity extends BaseActivity {

   private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Debug.startMethodTracing("jg");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=findViewById(R.id.bt_test_dialog);

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int  totalMemory= (int) (Runtime.getRuntime().totalMemory() / 1024);
        int freeMemory=(int)(Runtime.getRuntime().freeMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB");
        Log.d("TAG", "Total memory is " + totalMemory + "KB");
        Log.d("TAG", "freeMemory memory is " + freeMemory + "KB");

        Map<Integer,Integer> map=new HashMap<>();
        map.put(1,2);
        map.put(1,3);
        Log.d("TAG", "map get(1)="+map.get(1));





        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
               intent.setClass(MainActivity.this,LeakDemoActivity.class);
                MainActivity.this.startActivity(intent);
                final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("测试生命周期");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("MainActiviy",""+('b'-'a')+" int  1 & 100 ="+String.valueOf(1&100));

                       char[][] c={{'a','b','c'},{'d','e','f'},{'g','h','k'}};
                        String a="adg";
                        String b="ababcfk";
                        Log.e("TestCoding", "a result="+TestCoding.findTargetString(c,a)+ ",  b result="+TestCoding.findTargetString(c,b));

                        int number=15;
                        int[] intArray=new int[number];
                        for(int i=0;i<number;i++){
                            intArray[i]=(int)(Math.random()*100);
                            Log.e("sort origin",i+":"+intArray[i]);
                        }
                        int[] result=new QuickSort().ascendingSort(intArray);
                        for(int j=0;j<number;j++){
                            Log.e("sort result",j+":"+result[j]+" Integer.MIN_VALUE"+Integer.MIN_VALUE);
                        }




                        dialog.dismiss();
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
                builder.show();
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
        });


    }

    private Handler myHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
               mButton.setText("what =1");
            }else{
                mButton.setText("what! =1");
            }
        }

    };

}

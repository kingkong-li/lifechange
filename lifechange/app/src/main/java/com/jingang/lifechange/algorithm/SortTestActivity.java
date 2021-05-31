package com.jingang.lifechange.algorithm;

import android.os.Bundle;
import android.util.Log;

import com.jingang.lifechange.R;
import com.jingang.lifechange.algorithm.dynamic.dynamicPlan;
import com.jingang.lifechange.algorithm.sort.QuickSort;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.utils.PublicThreadPools;

/**
 * @Description: 测试分类算法的Activity
 * @Author:
 * @CreateTime:2021/5/25-11:09 AM
 * @changeTime:
 */
public class SortTestActivity extends BaseActivity {
    private static final String TAG=SortTestActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        PublicThreadPools.getService().submit(new Runnable() {
            @Override
            public void run() {

                Log.e(TAG,""+('b'-'a')+" int  1 & 100 ="+String.valueOf(1&100));

                char[][] c={{'a','b','c'},{'d','e','f'},{'g','h','k'}};
                String a="adg";
                String b="ababcfk";
                Log.e("TestCoding", "a result="+ dynamicPlan.findTargetString(c,a)+ ", " +
                        " b result="+ dynamicPlan.findTargetString(c,b));

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
            }
        });
        PublicThreadPools.getService().execute(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName(" my TaskName");
            }
        });



    }

    @Override
    public String getTag() {
        return "分类算法";
    }
}

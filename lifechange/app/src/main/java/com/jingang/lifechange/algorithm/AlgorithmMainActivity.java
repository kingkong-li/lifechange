package com.jingang.lifechange.algorithm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jingang.lifechange.R;
import com.jingang.lifechange.algorithm.search.BinarySearch;
import com.jingang.lifechange.algorithm.sort.SortTestActivity;
import com.jingang.lifechange.algorithm.struct.StructMainActivity;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.utils.PublicThreadPools;

public class AlgorithmMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm_main);
        Button button1=findViewById(R.id.bt_learn_struct);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(AlgorithmMainActivity.this, StructMainActivity.class);
                AlgorithmMainActivity.this.startActivity(intent);
            }
        });
        Button button2=findViewById(R.id.sort);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(AlgorithmMainActivity.this, SortTestActivity.class);
                AlgorithmMainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        PublicThreadPools.getService().submit(new Runnable() {
            @Override
            public void run() {
                int[] array=new int[]{1,2,3,4,6,7,8,9,10,13,17};
                BinarySearch binarySearch=new BinarySearch();
                for(int i=0;i<10;i++){
                    Log.v(getTag(),i+" indexof array is "+binarySearch.findTargetIndex(array,
                            i));
                }

            }
        });
    }

    @Override
    protected String getLabel() {
        return "算法主页";
    }
}
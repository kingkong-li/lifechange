package com.jingang.lifechange.algorithm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingang.lifechange.R;
import com.jingang.lifechange.algorithm.struct.StructMainActivity;
import com.jingang.lifechange.algorithm.struct.map.MapUseActivity;
import com.jingang.lifechange.base.BaseActivity;

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
    protected String getLabel() {
        return "算法主页";
    }
}
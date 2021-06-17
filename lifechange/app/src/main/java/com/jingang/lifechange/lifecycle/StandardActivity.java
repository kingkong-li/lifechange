package com.jingang.lifechange.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

/**
 * 启动模式 和 Activity 启动流程结合起来 讲解下
 */
public class StandardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(StandardActivity.this, StandardActivity.class);
                StandardActivity.this.startActivity(intent);
            }
        });
    }
}
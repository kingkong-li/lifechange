package com.jingang.lifechange.socket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

public class SocketMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_main);
        initView();

    }

    private void initView() {
        findViewById(R.id.bt_go_socket_server).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(SocketMainActivity.this, SocketServerActivity.class);
                SocketMainActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.bt_go_socket_client).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(SocketMainActivity.this, SocketClientActivity.class);
                SocketMainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected String getLabel() {
        return "Socket通信主页";
    }
}
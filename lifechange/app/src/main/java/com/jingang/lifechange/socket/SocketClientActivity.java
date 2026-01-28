package com.jingang.lifechange.socket;

import android.os.Bundle;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

public class SocketClientActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);
    }
    @Override
    protected String getLabel() {
        return "Socket客户端";
    }
}
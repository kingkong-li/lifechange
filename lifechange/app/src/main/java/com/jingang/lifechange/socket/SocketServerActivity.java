package com.jingang.lifechange.socket;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.utils.PublicThreadPools;

import java.io.IOException;

public class SocketServerActivity extends BaseActivity {
    private static final String TAG = "SocketServerActivity";
    private Button mOpenHotSpotButton;
    private TextView mHotSpotInfoTextView;

    private Button mOpenServerButton;
    private TextView mServerInfoTextView;

    private boolean isServerContainerRunning = false;

    SocketServer server = new SocketServer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isServerContainerRunning = true;
        setContentView(R.layout.activity_socket_server);
        mOpenHotSpotButton = findViewById(R.id.bt_open_Hotspot);
        mHotSpotInfoTextView = findViewById(R.id.tv_hot_spot_info);
        mServerInfoTextView = findViewById(R.id.tv_server_info);
        mOpenHotSpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HotspotHelper.getInstance().isHotspotActive()) {
                    HotspotHelper.getInstance().stopHotspot();
                    mOpenHotSpotButton.setText("开启热点");
                    mHotSpotInfoTextView.setText("");
                } else {
                    HotspotHelper.getInstance().startHotspot(config -> {
                        mOpenHotSpotButton.setText("关闭热点");
                        String ssid = config.getSsid();
                        String password = config.getPassphrase();
                        Log.d(TAG, "热点名称=" + ssid + "热点密码 =" + password);
                        mHotSpotInfoTextView.setText("热点名称=" + ssid + "     热点密码 =" + password);

                        // UI 显示给另一台手机
                    });
                }


            }
        });
        mOpenServerButton = findViewById(R.id.bt_open_server);
        mOpenServerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mOpenServerButton.getText() == "关闭服务") {
                    server.stop();
                    mOpenServerButton.setText("开启服务");
                    mServerInfoTextView.setText("");
                } else {
                    server.start(new SocketServer.ClientHandler() {
                        @Override
                        public void onServerStart(String ip, int port) {
                            Log.i(TAG, "server start ip=" + ip + " port=" + port);
                            mServerInfoTextView.setText("socket服务开启 ip=" + ip + " 端口port=" + port);
                            mOpenServerButton.setText("关闭服务");
                        }

                        @Override
                        public void onClient(SocketConnection conn) {
                            PublicThreadPools.getService().submit(new Runnable() {

                                @Override
                                public void run() {
                                    handleClient(conn);
                                }
                            });

                        }
                    });

                }
            }
        });

    }

    @Override
    protected String getLabel() {
        return "Socket服务器";
    }

    @Override
    protected void onStop() {
        HotspotHelper.getInstance().stopHotspot();
        isServerContainerRunning = false;
        super.onStop();
    }

    private void handleClient(SocketConnection conn) {
        Log.d(TAG, "handleClient" + conn.getClientAddress() + ":" + conn.getClientPort());
        try {
            while (isServerContainerRunning) {
                String msg = conn.readString();
                Log.d(TAG, "handleClient receive msg=" + msg);
                // 业务协议处理
                conn.sendString("ACK:" + msg);
            }
        } catch (IOException e) {
            Log.d("Server", "client disconnected");
        } finally {
            conn.close();
        }
    }

}
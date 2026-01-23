package com.jingang.lifechange.blue.tooth;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.jingang.lifechange.R;
import com.jingang.lifechange.utils.LiveDataBus;

import java.io.IOException;

public class BluetoothService extends Service {
    private static final String TAG = "BluetoothService";
    private BtServer btServer;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化蓝牙服务器
        btServer = new BtServer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 1. 适配 Android 8.0+ 的前台服务通知
        createNotificationChannel();
        Notification notification = new NotificationCompat.Builder(this, "BT_CHANNEL")
                .setContentTitle("蓝牙服务运行中")
                .setContentText("正在等待设备连接...")
                .setSmallIcon(R.mipmap.launcher)
                .build();

        // 2. 启动为前台服务 (ID 不能为 0)
        startForeground(1, notification);

        startBtServer();

        return START_STICKY; // 如果服务被杀，系统会尝试重启它
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "BT_CHANNEL", "Bluetooth Service",
                    NotificationManager.IMPORTANCE_LOW
            );
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }
    }

    private void startBtServer() {
        Log.i(TAG, "startBtServer");
        btServer.setListener(new BtServer.Listener() {
            @Override
            public void onClientConnected(BtConnection connection) {
                Log.d(TAG, "onClientConnected address= " + connection.getConnectDeviceAddress()+
                        ",connected name="+connection.getConnectDeviceName());
                connection.setListener(new BtConnection.Listener() {
                    @Override
                    public void onMessage(byte[] data, int length) {
                        String dataSet = new String(data, 0, length);
                        Log.d(TAG, "data from remote " + dataSet);
                        BlueDeviceMessage message = new BlueDeviceMessage();
                        message.deviceName = connection.getConnectDeviceName();
                        message.deviceAddress = connection.getConnectDeviceAddress();
                        message.data = dataSet;
                        LiveDataBus.BLUE_MESSAGE.postValue(message);

                    }

                    @Override
                    public void onDisconnected(Exception e) {
                        Log.d(TAG, "data from remote" + "Disconnected");
                    }
                });
            }
        });
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "SERVER " + "No connect permission");
                return;
            }
            btServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
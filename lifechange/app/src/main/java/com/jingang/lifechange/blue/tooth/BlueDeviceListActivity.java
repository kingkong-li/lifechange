package com.jingang.lifechange.blue.tooth;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.utils.PublicThreadPools;

import java.io.IOException;

public class BlueDeviceListActivity extends BaseActivity {
    private static final String TAG = BlueDeviceListActivity.class.getSimpleName();
    private DeviceDiscoveryViewModel deviceDiscoveryViewModel;
    private static final int PERMISSION_REQUEST_CODE = 1001;
    private RecyclerView mBluetoothDeviceList;
    private BlueListAdapter mBlueListAdapter;
    private TextView mCurrentConnectDeviceInfoTextView;
    private TextView mRemoteDataTextView;
    BtServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_device_list);
        mBluetoothDeviceList = findViewById(R.id.blue_tooth_device_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mBluetoothDeviceList.setLayoutManager(layoutManager);
        mBlueListAdapter = new BlueListAdapter();
        mBluetoothDeviceList.setAdapter(mBlueListAdapter);
        mCurrentConnectDeviceInfoTextView = findViewById(R.id.server_content_title);
        mRemoteDataTextView = findViewById(R.id.blue_server_content);

        deviceDiscoveryViewModel = new DeviceDiscoveryViewModel();
        deviceDiscoveryViewModel.getNewDevices().observe(this, peers -> {
            mBlueListAdapter.setData(peers);
        });


    }

    private void setBlueListListener() {
        BtClient client = new BtClient();
        mBlueListAdapter.setOnClickListener(new ItemSelectListener() {
            @Override
            public void onItemSelect(BtPeer peer) {
                // 跳转到蓝牙聊条页面
                BlueChatActivity.start(BlueDeviceListActivity.this, peer.name,peer.address);

                Log.v(TAG, "onItemSelect blueDevice=" + peer.address);
                PublicThreadPools.getService().submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (ActivityCompat.checkSelfPermission(BlueDeviceListActivity.this,
                                    Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED
                                    || ActivityCompat.checkSelfPermission(BlueDeviceListActivity.this,
                                    Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                                Log.e(TAG, "CLIENT " + "No connect permission");
                                return;
                            }
                            BtConnection connection = client.connect(peer.address);
                            Log.d(TAG, "CLIENT " + "Connected");
                            connection.setListener(new BtConnection.Listener() {
                                @Override
                                public void onMessage(byte[] data, int length) {
                                    Log.d(TAG, "CLIENT " + new String(data, 0, length));
                                }

                                @Override
                                public void onDisconnected(Exception e) {
                                    Log.d(TAG, "CLIENT " + "Disconnected");
                                }
                            });
                            connection.send("Hello i am client".getBytes());
                        } catch (Exception e) {
                            Log.e(TAG, "CLIENT " + "Error=" + e);
                        }
                    }
                });


            }
        });
    }

    private void startBtServer() {
        Log.i(TAG, "startBtServer");
        server = new BtServer();
        server.setListener(new BtServer.Listener() {
            @Override
            public void onClientConnected(BtConnection connection) {
                Log.d(TAG, "onClientConnected address= " + connection.getConnectDeviceAddress());
                PublicThreadPools.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCurrentConnectDeviceInfoTextView.setText("当前连接的设备名称：" + connection.getConnectDeviceName() +
                                "，当前连接的设备地址 " + connection.getConnectDeviceAddress());
                    }
                });

                connection.setListener(new BtConnection.Listener() {
                    @Override
                    public void onMessage(byte[] data, int length) {
                        String dataSet = new String(data, 0, length);
                        Log.d(TAG, "data from remote " + dataSet);
                        PublicThreadPools.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mRemoteDataTextView.append(dataSet + "\n");
                            }
                        });
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
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
                != PackageManager.PERMISSION_GRANTED)
                && (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT,
                                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            }
        } else {
            deviceDiscoveryViewModel.start(this);
            startBtServer();
            setBlueListListener();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.BLUETOOTH_SCAN}, PERMISSION_REQUEST_CODE);
            }
        }
        deviceDiscoveryViewModel.stop(this);
        if (server != null) {
            server.stop();
        }


    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                deviceDiscoveryViewModel.start(this);
                startBtServer();
                setBlueListListener();
            } else {
                Log.e("BlueDeviceListActivity", "onRequestPermissionsResult: Permission denied");
            }
        }
    }

    @Override
    protected String getLabel() {
        return "附近蓝牙设备列表";
    }
}
package com.jingang.lifechange.blue.tooth;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.utils.PublicThreadPools;

import java.util.List;

public class BlueDeviceListActivity extends BaseActivity {
    private static final String TAG = BlueDeviceListActivity.class.getSimpleName();
    private DeviceDiscoveryViewModel deviceDiscoveryViewModel;
    private static final int PERMISSION_REQUEST_CODE = 1001;
    private RecyclerView mBluetoothDeviceList;
    private BlueListAdapter mBlueListAdapter;

    private RecyclerView mBlueChatList;
    private BlueListAdapter mBlueChatAdapter;


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

        mBlueChatList = findViewById(R.id.history_chat_device_list);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mBlueChatList.setLayoutManager(layoutManager1);
        mBlueChatAdapter = new BlueListAdapter();
        mBlueChatList.setAdapter(mBlueChatAdapter);
        List<BtPeer> peerList = BtPeerStore.get(this).getAll();
        mBlueChatAdapter.setData(peerList);


        deviceDiscoveryViewModel = new DeviceDiscoveryViewModel();
        deviceDiscoveryViewModel.getNewDevices().observe(this, peers -> {
            mBlueListAdapter.setData(peers);
        });


    }

    private void setBlueListListener() {

        mBlueListAdapter.setOnClickListener(new ItemSelectListener() {
            @Override
            public void onItemSelect(BtPeer peer) {
                Log.v(TAG, "mBlueListAdapter onItemSelect blueDevice=" + peer.address);
                // 跳转到蓝牙聊条页面
                BlueChatActivity.start(BlueDeviceListActivity.this, peer.name,peer.address);
                BtPeerStore.get(BlueDeviceListActivity.this).add(peer);
                mBlueChatAdapter.setData(BtPeerStore.get(BlueDeviceListActivity.this).getAll());




            }
        });

        mBlueChatAdapter.setOnClickListener(new ItemSelectListener() {
            @Override
            public void onItemSelect(BtPeer peer) {
                Log.v(TAG, "mBlueChatAdapter onItemSelect   blueDevice=" + peer.address);
                // 跳转到蓝牙聊条页面
                BlueChatActivity.start(BlueDeviceListActivity.this, peer.name,peer.address);
            }
        });
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
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

public class BlueDeviceListActivity extends BaseActivity {
    private static final String TAG = BlueDeviceListActivity.class.getSimpleName();
    private DeviceDiscoveryViewModel deviceDiscoveryViewModel;
    private static final int PERMISSION_REQUEST_CODE = 1001;
    private RecyclerView mBluetoothDeviceList;
    private BlueListAdapter mBlueListAdapter;

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

        deviceDiscoveryViewModel = new DeviceDiscoveryViewModel();
        deviceDiscoveryViewModel.getNewDevices().observe(this, peers -> {
            Log.v(TAG, "find peers=" + peers.toString());
            mBlueListAdapter.setData(peers);
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
                != PackageManager.PERMISSION_GRANTED)
        && (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)!=PackageManager.PERMISSION_GRANTED)
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT,
                        Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            }
        } else {
            deviceDiscoveryViewModel.start(this);
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
            } else {
               Log.e("BlueDeviceListActivity", "onRequestPermissionsResult: Permission denied");
            }
        }
    }

}
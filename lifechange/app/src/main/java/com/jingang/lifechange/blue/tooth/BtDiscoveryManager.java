package com.jingang.lifechange.blue.tooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;

import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;

import com.jingang.lifechange.utils.BlueDeviceVendorHelper;

import java.util.Arrays;
import java.util.List;

public class BtDiscoveryManager {
    private final String TAG = "BtDiscoveryManager";
    private final BluetoothAdapter adapter =
            BluetoothAdapter.getDefaultAdapter();

    private final BtDeviceCache cache = new BtDeviceCache();
    private BtDiscoveryListener listener;

    private BluetoothLeScanner mScanner;


    public void setListener(BtDiscoveryListener listener) {
        this.listener = listener;
    }


    public void start() {
        mScanner = adapter.getBluetoothLeScanner();
        if (mScanner != null) {
            mScanner.startScan(scanCallback);
        }

    }
    private final ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            BluetoothDevice device = result.getDevice();
            ScanRecord record = result.getScanRecord();
            BtPeer peer = null;
            if (device != null) {
                Log.v(TAG, "onScanResult device:name=" + device.getName()+",address="+device.getAddress()
                +",rssi="+result.getRssi());
                peer = new BtPeer(
                        device.getAddress(),
                        device.getName(),
                        result.getRssi(),
                        System.currentTimeMillis(),
                        BlueDeviceVendorHelper.getVendorName(record)
                );
            }

            cache.put(peer);
            if (listener != null) listener.onDeviceFound(peer);
        }
    };

    @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
    public void stop() {
        Log.v(TAG, "stop");
        if (mScanner != null) {
            mScanner.stopScan(scanCallback);
        }
    }

    public List<BtPeer> getCachedDevices() {
        return cache.snapshot();
    }
}

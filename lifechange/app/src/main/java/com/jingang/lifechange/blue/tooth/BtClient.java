package com.jingang.lifechange.blue.tooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import androidx.annotation.RequiresPermission;

import java.io.IOException;

public class BtClient {

    private final BluetoothAdapter adapter;

    public BtClient() {
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN})
    public BtConnection connect(String remoteDeviceAddress) throws IOException {
        BluetoothDevice device = adapter.getRemoteDevice(remoteDeviceAddress);
        BluetoothSocket socket =
                device.createRfcommSocketToServiceRecord(
                        BtConst.SERVICE_UUID
                );
        // 建立连接
        socket.connect(); // 阻塞
        // 执行到这里客户端服务端你已经建立连接了
        BtConnection conn = new BtConnection(socket);
        conn.startReadData();
        return conn;
    }
}

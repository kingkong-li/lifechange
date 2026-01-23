package com.jingang.lifechange.blue.tooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import androidx.annotation.RequiresPermission;

import com.jingang.lifechange.utils.ToastUtil;

import java.io.IOException;

public class BtServer {

    private static final String TAG = BtServer.class.getSimpleName();

    private final BluetoothAdapter adapter;
    private BluetoothServerSocket serverSocket;
    private volatile boolean running = true;

    public interface Listener {
        void onClientConnected(BtConnection connection);
    }

    private Listener listener;

    public BtServer() {
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    public void start() throws IOException {
        if (adapter == null || !adapter.isEnabled()) {
            Log.e("BT", "Bluetooth is not available or disabled");
            ToastUtil.getInstance().show("蓝牙未开启");
            return;
        }

        serverSocket =
                adapter.listenUsingRfcommWithServiceRecord(
                        "BtServer",
                        BtConst.SERVICE_UUID
                );

        new Thread(new Runnable() {
            @Override
            public void run() {
                acceptLoop();
            }
        }, "BtAcceptThread").start();
    }

    private void acceptLoop() {
        while (running) {
            try {
                BluetoothSocket socket = serverSocket.accept(); // 阻塞
                if (socket == null) {
                    return;
                }
                BluetoothDevice device = socket.getRemoteDevice();
                Log.d(TAG, "acceptLoop: " + device.getName() + " " + device.getAddress());
                if (listener != null) {
                    BtConnection conn = new BtConnection(socket);
                    conn.send("welcome!! I am server".getBytes());
                    listener.onClientConnected(conn);
                    conn.startReadData();
                }
            } catch (IOException e) {
                if (!running) break;
            }
        }
    }

    public void stop() {
        running = false;
        try {
            serverSocket.close(); // 打断 accept
        } catch (IOException ignored) {}
    }
}

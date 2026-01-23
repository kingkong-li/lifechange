package com.jingang.lifechange.blue.tooth;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BtConnection {
    private static final String TAG = "BtConnection";

    private final BluetoothSocket socket;
    private final InputStream in;
    private final OutputStream out;

    private volatile boolean running = true;
    private Thread readThread;

    private boolean isConnected = true;

    public boolean isConnected() {
        return isConnected;
    }

    public interface Listener {
        void onMessage(byte[] data, int length);
        void onDisconnected(Exception e);
    }

    private Listener listener;

    public BtConnection(BluetoothSocket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        isConnected = true;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void startReadData() {

        readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                readLoop();
            }
        }, "BtReadThread");
        readThread.start();
    }

    private void readLoop() {
        byte[] buffer = new byte[1024];
        try {
            while (running) {
                int len = in.read(buffer); // 阻塞点
                if (len == -1) break;
                if (listener != null) {
                    listener.onMessage(buffer, len);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "readLoop: " + e);
            isConnected = false;
            if (listener != null) {
                listener.onDisconnected(e);
            }
        } finally {
            close();
        }
    }

    public synchronized void send(byte[] data) throws IOException {
        out.write(data);
        out.flush();
    }

    public synchronized void close() {
        running = false;
        try {
            isConnected = false;
            socket.close(); // 会唤醒 read()
        } catch (IOException ignored) {}
    }

    public String getConnectDeviceName () {
        String name = "";
       if(socket!=null){
           name = socket.getRemoteDevice().getName();
       }
       return name;
    }
    public String getConnectDeviceAddress () {
        String address = "";
        if(socket!=null){
            address = socket.getRemoteDevice().getAddress();
        }
        return address;
    }
}

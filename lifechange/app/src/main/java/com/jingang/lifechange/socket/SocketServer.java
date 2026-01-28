package com.jingang.lifechange.socket;

import android.util.Log;

import com.jingang.lifechange.utils.PublicThreadPools;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;

// socket/SocketServer.java
public class SocketServer {

    private ServerSocket serverSocket;
    private int port;

    public interface ClientHandler {
        void onServerStart(String ip,int port);
        void onClient(SocketConnection conn);
    }

    public void start(ClientHandler handler) {
        try {
            serverSocket = new ServerSocket(0);
        } catch (IOException e) {
            // 这里就是一个值得思考的点，这个异常应该是哪一层处理的
           Log.e("SocketServer", "start error", e);
        }
        port = serverSocket.getLocalPort();
        handler.onServerStart(getLocalIp(), port);

        PublicThreadPools.getService().execute(() -> {
            while (!serverSocket.isClosed()) {
                try {
                    Socket socket = serverSocket.accept();
                    SocketConnection conn =
                            new SocketConnection(socket);
                    handler.onClient(conn);
                } catch (IOException e) {
                    Log.e("SocketServer", "accept error", e);
                    break;
                }
            }
        });
    }

    public int getPort() {
        return port;
    }

    public void stop() {
        try { serverSocket.close(); } catch (Exception ignored) {}
    }


    public String getLocalIp() {
        try {
            for (Enumeration<NetworkInterface> en =
                 NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements();) {

                NetworkInterface intf = en.nextElement();

                for (Enumeration<InetAddress> enumIpAddr =
                     intf.getInetAddresses();
                     enumIpAddr.hasMoreElements();) {

                    InetAddress addr = enumIpAddr.nextElement();
                    if (!addr.isLoopbackAddress()
                            && addr instanceof Inet4Address) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (Exception ignored) {}
        return null;
    }
}


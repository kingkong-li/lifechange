package com.jingang.lifechange.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

// socket/SocketConnection.java
public class SocketConnection {

    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    public SocketConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    public void sendString(String msg) throws IOException {
        out.writeUTF(msg);
        out.flush();
    }

    public String readString() throws IOException {
        return in.readUTF();
    }

    public void close() {
        try { socket.close(); } catch (Exception ignored) {}
    }

    public String getClientAddress() {
        if(socket ==  null){
            return "";
        }
        return socket.getInetAddress().getHostAddress();
    }

    public String getClientPort() {
        if (socket == null) {
            return "";
        }
        return String.valueOf(socket.getPort());
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    public InputStream getInputStream() {
        return in;
    }
    public OutputStream getOutputStream() {
        return out;
    }
}


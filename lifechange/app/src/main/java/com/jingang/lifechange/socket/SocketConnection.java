package com.jingang.lifechange.socket;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnection implements Parcelable {

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

    // Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Socket cannot be serialized, so we'll handle this differently
        // We'll create a new connection in the target activity instead
    }

    public static final Parcelable.Creator<SocketConnection> CREATOR = new Parcelable.Creator<SocketConnection>() {
        @Override
        public SocketConnection createFromParcel(Parcel source) {
            // This won't actually create a valid connection
            // We'll handle connection creation differently
            return null;
        }

        @Override
        public SocketConnection[] newArray(int size) {
            return new SocketConnection[size];
        }
    };
}


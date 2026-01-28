package com.jingang.lifechange.socket;

import java.io.IOException;
import java.net.Socket;

public class SocketClient {

    public SocketConnection connect(String ip, int port)
            throws IOException {
        Socket socket = new Socket(ip, port);
        return new SocketConnection(socket);
    }
}

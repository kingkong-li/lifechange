package com.jingang.lifechange.socket;

import java.util.HashMap;
import java.util.Map;

public class SocketConnectionManager {
    private static SocketConnectionManager instance;
    private Map<String, SocketConnection> connectionMap;
    private int connectionCounter = 0;

    private SocketConnectionManager() {
        connectionMap = new HashMap<>();
    }

    public static synchronized SocketConnectionManager getInstance() {
        if (instance == null) {
            instance = new SocketConnectionManager();
        }
        return instance;
    }

    public String addConnection(SocketConnection connection) {
        String connectionId = "conn_" + (connectionCounter++);
        connectionMap.put(connectionId, connection);
        return connectionId;
    }

    public SocketConnection getConnection(String connectionId) {
        return connectionMap.get(connectionId);
    }

    public void removeConnection(String connectionId) {
        SocketConnection connection = connectionMap.remove(connectionId);
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
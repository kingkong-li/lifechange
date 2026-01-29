package com.jingang.lifechange.socket;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.socket.adapter.ChatMessageAdapter;
import com.jingang.lifechange.socket.model.ChatMessage;
import com.jingang.lifechange.utils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SocketServerChatActivity extends BaseActivity {
    private static final String TAG = "SocketServerChatActivity";
    
    // UI组件
    private TextView tvClientInfo;
    private RecyclerView rvChatMessages;
    private EditText etInputMessage;
    private Button btnSend;
    private ChatMessageAdapter chatMessageAdapter;
    private List<ChatMessage> chatMessages = new ArrayList<>();
    private LayoutInflater inflater;
    
    // Socket连接
    private SocketConnection socketConnection;
    private boolean isConnected = false;
    private Thread receiveThread;
    private String connectionId;
    
    // 客户端信息
    private String clientAddress;
    private int clientPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_server_chat);
        
        // 获取客户端连接信息
        connectionId = getIntent().getStringExtra("connection_id");
        clientAddress = getIntent().getStringExtra("client_address");
        clientPort = getIntent().getIntExtra("client_port", 0);
        
        // 从连接管理器获取连接
        socketConnection = SocketConnectionManager.getInstance().getConnection(connectionId);
        
        initViews();
        setupRecyclerView();
        setupListeners();
        
        if (socketConnection != null && socketConnection.isConnected()) {
            isConnected = true;
            startReceiveThread();
            updateClientInfo();
        } else {
            ToastUtil.getInstance().show("连接无效或已断开");
            finish();
        }
    }

    private void initViews() {
        tvClientInfo = findViewById(R.id.tv_client_info);
        rvChatMessages = findViewById(R.id.rv_chat_messages);
        etInputMessage = findViewById(R.id.et_input_message);
        btnSend = findViewById(R.id.btn_send);
        inflater = LayoutInflater.from(this);
    }
    
    private void setupRecyclerView() {
        chatMessageAdapter = new ChatMessageAdapter(inflater, chatMessages);
        rvChatMessages.setLayoutManager(new LinearLayoutManager(this));
        rvChatMessages.setAdapter(chatMessageAdapter);
    }
    
    private void setupListeners() {
        btnSend.setOnClickListener(v -> sendMessage());
    }
    
    private void updateClientInfo() {
        if (tvClientInfo != null) {
            tvClientInfo.setText("客户端: " + clientAddress + ":" + clientPort);
        }
    }

    private void startReceiveThread() {
        receiveThread = new Thread(() -> {
            while (isConnected && !Thread.currentThread().isInterrupted()) {
                try {
                    String receivedMessage = socketConnection.readString();
                    if (receivedMessage != null) {
                        final String finalMessage = receivedMessage;
                        runOnUiThread(() -> {
                            chatMessages.add(new ChatMessage(finalMessage, false));
                            chatMessageAdapter.notifyItemInserted(chatMessages.size() - 1);
                            rvChatMessages.scrollToPosition(chatMessages.size() - 1);
                            ToastUtil.getInstance().show("收到消息: " + finalMessage);
                        });
                    }
                } catch (IOException e) {
                    if (isConnected) {
                        runOnUiThread(() -> {
                            ToastUtil.getInstance().show("客户端断开连接");
                            finish();
                        });
                    }
                    break;
                }
            }
        });
        receiveThread.start();
    }

    private void sendMessage() {
        if (!isConnected || socketConnection == null || !socketConnection.isConnected()) {
            ToastUtil.getInstance().show("客户端未连接");
            return;
        }

        String message = etInputMessage.getText().toString();
        if (message.isEmpty()) {
            ToastUtil.getInstance().show("消息不能为空");
            return;
        }

        new Thread(() -> {
            try {
                socketConnection.sendString(message);
                runOnUiThread(() -> {
                    chatMessages.add(new ChatMessage(message, true));
                    chatMessageAdapter.notifyItemInserted(chatMessages.size() - 1);
                    rvChatMessages.scrollToPosition(chatMessages.size() - 1);
                    etInputMessage.setText("");
                    ToastUtil.getInstance().show("消息已发送");
                });
            } catch (IOException e) {
                runOnUiThread(() -> {
                    ToastUtil.getInstance().show("发送失败: " + e.getMessage());
                    isConnected = false;
                });
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isConnected = false;
        if (receiveThread != null && receiveThread.isAlive()) {
            receiveThread.interrupt();
        }
        if (connectionId != null) {
            SocketConnectionManager.getInstance().removeConnection(connectionId);
        }
    }

    @Override
    protected String getLabel() {
        return "服务器聊天 - " + (clientAddress != null ? clientAddress : "未知客户端");
    }
}
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

public class SocketClientActivity extends BaseActivity {
    private static final String TAG = "SocketClientActivity";
    private EditText etServerIp, etServerPort, etInputMessage;
    private Button btnConnect, btnSend;
    private TextView tvConnectionStatus;
    private RecyclerView rvChatMessages;
    private ChatMessageAdapter chatMessageAdapter;
    private final List<ChatMessage> chatMessages = new ArrayList<>();
    private SocketConnection socketConnection;
    private SocketClient socketClient;

    private volatile boolean isConnected = false;
    private Thread receiveThread;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);
        
        socketClient = new SocketClient();
        initViews();
        setupListeners();
    }

    private void initViews() {
        etServerIp = findViewById(R.id.et_server_ip);
        etServerPort = findViewById(R.id.et_server_port);
        etInputMessage = findViewById(R.id.et_input_message);
        btnConnect = findViewById(R.id.btn_connect);
        btnSend = findViewById(R.id.btn_send);
        tvConnectionStatus = findViewById(R.id.tv_connection_status);
        rvChatMessages = findViewById(R.id.rv_chat_messages);
        inflater = LayoutInflater.from(this);
        
        setupRecyclerView();
    }
    
    private void setupRecyclerView() {
        chatMessageAdapter = new ChatMessageAdapter(inflater, chatMessages);
        rvChatMessages.setLayoutManager(new LinearLayoutManager(this));
        rvChatMessages.setAdapter(chatMessageAdapter);
    }

    private void setupListeners() {
        btnConnect.setOnClickListener(v -> connectToServer());
        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void connectToServer() {
        if (isConnected) {
            disconnectFromServer();
            return;
        }

        new Thread(() -> {
            try {
                String ip = etServerIp.getText().toString();
                int port = Integer.parseInt(etServerPort.getText().toString());
                socketConnection = socketClient.connect(ip, port);


                isConnected = true;
                
                runOnUiThread(() -> {
                    tvConnectionStatus.setText("已连接到 " + ip + ":" + port);
                    tvConnectionStatus.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light, null));
                    btnConnect.setText("断开连接");
                    btnSend.setEnabled(true);
                    ToastUtil.getInstance().show("连接成功");
                });

                // 启动接收消息线程
                startReceiveThread();
                
            } catch (IOException e) {
                runOnUiThread(() -> {
                    ToastUtil.getInstance().show("连接失败: " + e.getMessage());
                    resetConnectionState();
                });
                Log.e(TAG, "CLIENT " + "Error connecting to server: " + e);
            } catch (NumberFormatException e) {
                runOnUiThread(() -> ToastUtil.getInstance().show("端口号格式错误"));
            }
        }).start();
    }

    private void disconnectFromServer() {
        isConnected = false;
        if (receiveThread != null && receiveThread.isAlive()) {
            receiveThread.interrupt();
        }
        if (socketConnection != null) {
            socketConnection.close();
        }
        runOnUiThread(() -> {
            resetConnectionState();
            ToastUtil.getInstance().show("已断开连接");
        });
    }

    private void resetConnectionState() {
        runOnUiThread(() -> {
            tvConnectionStatus.setText("未连接");
            tvConnectionStatus.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light,null));
            btnConnect.setText("连接");
            btnSend.setEnabled(false);
        });
    }

    private void startReceiveThread() {
        receiveThread = new Thread(() -> {
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while (isConnected && !Thread.currentThread().isInterrupted()) {
                try {
                    if (socketConnection.getInputStream() != null &&
                            (bytesRead = socketConnection.getInputStream().read(buffer)) != -1) {
                        String receivedMessage = new String(buffer, 0, bytesRead);
                        runOnUiThread(() -> {
                            chatMessages.add(new ChatMessage(receivedMessage, false));
                            chatMessageAdapter.notifyItemInserted(chatMessages.size() - 1);
                            rvChatMessages.scrollToPosition(chatMessages.size() - 1);
                            ToastUtil.getInstance().show("收到消息: " + receivedMessage);
                        });
                    }
                } catch (IOException e) {
                    if (isConnected) {
                        runOnUiThread(() -> {
                            ToastUtil.getInstance().show("连接异常断开");
                            resetConnectionState();
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
            ToastUtil.getInstance().show("请先连接服务器");
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
                    resetConnectionState();
                });
              Log.e(TAG, TAG + "Error sending message: " + e);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disconnectFromServer();
    }

    @Override
    protected String getLabel() {
        return "Socket聊天客户端";
    }
}
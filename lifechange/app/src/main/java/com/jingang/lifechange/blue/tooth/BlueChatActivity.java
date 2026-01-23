package com.jingang.lifechange.blue.tooth;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;
import com.jingang.lifechange.utils.LiveDataBus;
import com.jingang.lifechange.utils.PublicThreadPools;

import java.util.ArrayList;
import java.util.List;

public class BlueChatActivity extends BaseActivity {
    private final String TAG = "BlueChatActivity";
    private static final String EXTRA_BLUE_ADDRESS = "blue_address";
    private static final String EXTRA_BLUE_DEVICE_NAME = "blue_device_name";
    private RecyclerView mChatMessageRecyclerView;
    private EditText mInputMessageEditText;
    private Button mMessageSendButton;
    private ChatMessageAdapter chatMessageAdapter;
    private List<ChatMessage> chatMessages;
    BtClient client = new BtClient();
    String mBlueDeviceMacAddress;


    public static void start(Context context, String blueDeviceName, String address) {
        Intent intent = new Intent(context, BlueChatActivity.class);
        intent.putExtra(EXTRA_BLUE_DEVICE_NAME, blueDeviceName);
        intent.putExtra(EXTRA_BLUE_ADDRESS, address);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_chat);

        initViews();
        setupRecyclerView();
        setupSendButton();
        // 处理第一次启动时的 Intent
        handleIntent(getIntent());
        addMessageReceiver();
    }

    private void addMessageReceiver() {
        LiveDataBus.BLUE_MESSAGE.observe( this, message -> {
            Log.d(TAG, "receive message: " + message);
            ChatMessage receivedMessage = new ChatMessage(message.data, false);
            chatMessages.add(receivedMessage);
            chatMessageAdapter.notifyItemInserted(chatMessages.size() - 1);
            mChatMessageRecyclerView.scrollToPosition(chatMessages.size() - 1);
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 必须要调用 setIntent，否则 getIntent() 拿到的还是旧的 Intent
        setIntent(intent);

        // 处理后续启动时的 Intent
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (intent != null) {
            String blueDeviceName = intent.getStringExtra(EXTRA_BLUE_DEVICE_NAME);
            mBlueDeviceMacAddress = intent.getStringExtra(EXTRA_BLUE_ADDRESS);
            String pageTitle = blueDeviceName;
            if(TextUtils.isEmpty(blueDeviceName)){
                pageTitle = mBlueDeviceMacAddress;
            }
            setPageTitle(pageTitle);
            // 更新 UI 或逻辑
        }
    }

    private void initViews() {
        mChatMessageRecyclerView = findViewById(R.id.rv_chat_messages);
        mInputMessageEditText = findViewById(R.id.et_input_message);
        mMessageSendButton = findViewById(R.id.btn_send);
    }


    private void setupRecyclerView() {
        chatMessages = new ArrayList<>();
        chatMessageAdapter = new ChatMessageAdapter(this, chatMessages);
        mChatMessageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mChatMessageRecyclerView.setAdapter(chatMessageAdapter);
    }

    private void setupSendButton() {
        mMessageSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mInputMessageEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(message)) {
                    // 添加发送的消息
                    ChatMessage sentMessage = new ChatMessage(message, true);
                    chatMessages.add(sentMessage);
                    chatMessageAdapter.notifyItemInserted(chatMessages.size() - 1);
                    mChatMessageRecyclerView.scrollToPosition(chatMessages.size() - 1);

                    // 清空输入框
                    mInputMessageEditText.setText("");

                    // 蓝牙发送逻辑
                    sendMessageViaBluetooth(message);

                } else {
                    Toast.makeText(BlueChatActivity.this, "请输入消息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendMessageViaBluetooth(String message) {
        PublicThreadPools.getService().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ActivityCompat.checkSelfPermission(BlueChatActivity.this,
                            Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED
                            || ActivityCompat.checkSelfPermission(BlueChatActivity.this,
                            Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        Log.e(TAG, "CLIENT " + "No connect permission");
                        return;
                    }
                    BtConnection connection = client.connect(mBlueDeviceMacAddress);
                    Log.d(TAG, "CLIENT " + "Connected");
                    connection.setListener(new BtConnection.Listener() {
                        @Override
                        public void onMessage(byte[] data, int length) {
                            Log.d(TAG, "CLIENT " + new String(data, 0, length));
                        }

                        @Override
                        public void onDisconnected(Exception e) {
                            Log.d(TAG, "CLIENT " + "Disconnected");
                        }
                    });
                    connection.send(message.getBytes());
                } catch (Exception e) {
                    Log.e(TAG, "CLIENT " + "Error=" + e);
                }
            }
        });

    }

    @Override
    protected String getLabel() {
        return "蓝牙聊天";
    }

    private void setPageTitle(String title) {
        if (getSupportActionBar() != null) {
           getSupportActionBar().setTitle(title);
        }
    }
}
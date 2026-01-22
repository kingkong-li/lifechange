package com.jingang.lifechange.blue.tooth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

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
            String blueAddress = intent.getStringExtra(EXTRA_BLUE_ADDRESS);
            String pageTitle = blueDeviceName;
            if(TextUtils.isEmpty(blueDeviceName)){
                pageTitle = blueAddress;
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

                    // TODO: 这里可以添加蓝牙发送逻辑
                    // sendMessageViaBluetooth(message);

                    // 模拟接收一条消息（用于UI演示）
                    simulateReceivedMessage("收到: " + message);
                } else {
                    Toast.makeText(BlueChatActivity.this, "请输入消息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void simulateReceivedMessage(String message) {
        // 延迟1秒模拟接收消息
        mMessageSendButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                ChatMessage receivedMessage = new ChatMessage(message, false);
                chatMessages.add(receivedMessage);
                chatMessageAdapter.notifyItemInserted(chatMessages.size() - 1);
                mChatMessageRecyclerView.scrollToPosition(chatMessages.size() - 1);
            }
        }, 1000);
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
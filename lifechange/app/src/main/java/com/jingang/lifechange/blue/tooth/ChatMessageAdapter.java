package com.jingang.lifechange.blue.tooth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jingang.lifechange.R;

import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_SENT = 0;
    private static final int TYPE_RECEIVED = 1;
    
    private List<ChatMessage> messages;
    private LayoutInflater inflater;

    public ChatMessageAdapter(android.content.Context context, List<ChatMessage> messages) {
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = messages.get(position);
        return message.isSent() ? TYPE_SENT : TYPE_RECEIVED;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_SENT) {
            View view = inflater.inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        
        if (holder instanceof SentMessageViewHolder) {
            ((SentMessageViewHolder) holder).bind(message);
        } else if (holder instanceof ReceivedMessageViewHolder) {
            ((ReceivedMessageViewHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;

        SentMessageViewHolder(View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message);
        }

        void bind(ChatMessage message) {
            tvMessage.setText(message.getMessage());
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;

        ReceivedMessageViewHolder(View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message);
        }

        void bind(ChatMessage message) {
            tvMessage.setText(message.getMessage());
        }
    }
}
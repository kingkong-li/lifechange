package com.jingang.lifechange.blue.tooth;

public class ChatMessage {
    private String message;
    private boolean isSent; // true表示发送的消息，false表示接收的消息
    
    public ChatMessage(String message, boolean isSent) {
        this.message = message;
        this.isSent = isSent;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isSent() {
        return isSent;
    }
    
    public void setSent(boolean sent) {
        isSent = sent;
    }
}
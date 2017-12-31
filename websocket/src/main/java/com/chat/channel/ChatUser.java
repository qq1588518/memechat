package com.chat.channel;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.Data;

import java.util.UUID;

@Data
public class ChatUser {
    private UUID sessionId;
    private SocketIOClient socketIOClient;
    private String name;
    private int age;
    private int sex;
    private boolean channel;

    public ChatUser() {
    }
}

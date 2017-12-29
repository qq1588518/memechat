package com.chat.process;

import com.chat.channel.ChatUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserProcess {
    private static List<UUID> WAIT_USER = new ArrayList<>();
    private static Map<UUID, ChatUser> USER_MAP = new ConcurrentHashMap(32);

    private UserProcess() {
    }

    public static UserProcess process() {
        return UserHelper.$();
    }

    public void createUser(ChatUser user) {
        if (USER_MAP.containsKey(user.getSessionId())) {
            return;
        }
        USER_MAP.put(user.getSessionId(), user);
        this.addWaitUser(user.getSessionId());
    }

    public ChatUser getChatUser(UUID uuid) {
        return USER_MAP.get(uuid);
    }

    public void removeChatUser(UUID uuid) {
        USER_MAP.remove(uuid);
        this.removeWaitUser(uuid);
    }

    public void removeWaitUser(UUID uuid) {
        if (WAIT_USER.contains(uuid))
            WAIT_USER.remove(uuid);
    }

    public void addWaitUser(UUID uuid) {
        WAIT_USER.add(uuid);
    }

    public static class UserHelper {
        private final static UserProcess $ = new UserProcess();

        public static UserProcess $() {
            return $;
        }
    }
}

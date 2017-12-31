package com.chat.process;

import com.chat.channel.ChatUser;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserProcess {
    private static List<UUID> WAIT_USER = new ArrayList<>();
    private static Map<UUID, ChatUser> USER_MAP = new ConcurrentHashMap(32);
    private Random random = new Random();

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
    }

    public ChatUser getChatUser(UUID uuid) {
        return USER_MAP.get(uuid);
    }

    public void removeChatUser(UUID uuid) {
        USER_MAP.remove(uuid);
        this.removeWaitUser(uuid);
    }

    public void removeWaitUser(UUID... uuid) {
        for (UUID id : uuid) {
            if (WAIT_USER.contains(id))
                WAIT_USER.remove(id);
        }

    }

    public void addWaitUser(UUID uuid) {
        WAIT_USER.add(uuid);
    }

    public int getWaitUserLen() {
        return WAIT_USER.size();
    }

    public void match(UUID uuid) {
        if (1 < getWaitUserLen()) {
            ChatUser me, friend;
            while (true) {
                int pos = random.nextInt(getWaitUserLen());
                UUID friendId = WAIT_USER.get(pos);
                if (uuid == friendId) {
                    continue;
                }
                friend = getChatUser(friendId);
                me = getChatUser(uuid);
                removeWaitUser(uuid, friendId);
                break;
            }
            if (null != me && null != friend) {

            }
        }

    }

    public static class UserHelper {
        private final static UserProcess $ = new UserProcess();

        public static UserProcess $() {
            return $;
        }
    }
}

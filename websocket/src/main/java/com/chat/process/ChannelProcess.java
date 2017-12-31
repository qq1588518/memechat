package com.chat.process;

import com.chat.channel.Channel;
import com.chat.channel.ChatUser;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelProcess {
    private static Map<Long, Channel> CHANNEL_MAP = new ConcurrentHashMap(16);
    private static ChannelProcess CHANNEL_PROCESS;
    private UserProcess userProcess = UserProcess.process();
    private Random random = new Random();

    public static ChannelProcess process() {
        if (null == CHANNEL_PROCESS) {
            synchronized (ChannelProcess.class) {
                if (null == CHANNEL_PROCESS) {
                    CHANNEL_PROCESS = new ChannelProcess();
                }
            }
        }
        return CHANNEL_PROCESS;
    }


    public void joinChannel(ChatUser chatUser) {

    }
}

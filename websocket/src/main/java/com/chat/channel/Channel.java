package com.chat.channel;

import lombok.Data;

import java.util.*;

@Data
public class Channel {
    private Long id;
    private String name;
    private String remark;
    private List<UUID> channelUser;

    public Channel() {
        channelUser = new ArrayList<>(2);
    }

    public void join(UUID uuid){
        channelUser.add(uuid);
    }

    public void leave(UUID uuid){
        channelUser.remove(uuid);
    }

}

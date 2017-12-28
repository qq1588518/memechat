package com.chat.channel;

import java.util.HashMap;
import java.util.Map;

public class Channel {
    private String name;
    private Long id ;
    private Map<Long , User> userMap = new HashMap<>();

}

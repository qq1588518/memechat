package com.chat;

import com.chat.channel.ChatUser;
import com.chat.process.UserProcess;
import com.chat.vo.MessageVo;
import com.chat.vo.UserVo;
import com.corundumstudio.socketio.*;

public class Server {
    private Configuration configuration;
    private SocketIOServer socketIOServer;
    private static volatile Server SERVER;
    private UserProcess userProcess = UserProcess.process();

    public static Server me(String hostName, int port) {
        if (null == SERVER) {
            synchronized (Server.class) {
                if (null == SERVER) {
                    Configuration configuration = new Configuration();
                    configuration.setHostname("localhost");
                    configuration.setPort(10407);
                    SERVER = new Server(configuration);
                }
            }
        }
        return SERVER;
    }

    private Server(Configuration configuration) {
        this.configuration = configuration;
    }

    public void start() {
        socketIOServer = new SocketIOServer(this.configuration);
        final SocketIONamespace namespace = socketIOServer.addNamespace("/chat");
        namespace.addEventListener("connect", UserVo.class, (client, userVo, req) -> {
            ChatUser chatUser = new ChatUser();
            chatUser.setName(userVo.getName());
            chatUser.setSex(userVo.getSex());
            chatUser.setAge(userVo.getAge());
            chatUser.setSocketIOClient(client);
            this.userProcess.createUser(chatUser);
        });

        namespace.addEventListener("message", MessageVo.class, (client, messageVo, ackRequest) -> {
            //todo
        });

        namespace.addEventListener("disconnect", UserVo.class, (client, message, ackRequest) -> {
            //todo
        });

        socketIOServer.start();
    }

    public void stop() {
        if (null != this.socketIOServer)
            this.socketIOServer.stop();
    }

}

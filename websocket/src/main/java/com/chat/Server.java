package com.chat;

import com.chat.domain.Message;
import com.chat.domain.User;
import com.corundumstudio.socketio.*;

public class Server {
    private Configuration configuration;
    private SocketIOServer socketIOServer;
    private static volatile Server SERVER;

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
        namespace.addEventListener("connect", User.class, (client, user, req) -> {
            //todo
        });

        namespace.addEventListener("message", Message.class, (client, message, ackRequest) -> {
            //todo
        });

        namespace.addEventListener("disconnect", User.class, (client, message, ackRequest) -> {
            //todo
        });

        socketIOServer.start();
    }

    public void stop() {
        if (null != this.socketIOServer)
            this.socketIOServer.stop();
    }

}

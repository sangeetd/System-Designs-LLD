package com.sangeet.project.model;

import java.util.UUID;

public class MessaginQueueInfo {

    private String messagingQueueID;
    private String messagingQueueName;
    private String messagingQueueVersion;
    private Server hostServer;

    public MessaginQueueInfo(String messagingQueueName, String messagingQueueVersion, String ip, String port) {
        this.messagingQueueID = UUID.randomUUID().toString();
        this.messagingQueueName = messagingQueueName;
        this.messagingQueueVersion = messagingQueueVersion;
        this.hostServer = new Server(ip, port);
    }

    public String getMessagingQueueID() {
        return messagingQueueID;
    }

    public String getMessagingQueueName() {
        return messagingQueueName;
    }

    public String getMessagingQueueVersion() {
        return messagingQueueVersion;
    }

    public Server getHostServer() {
        return hostServer;
    }
}

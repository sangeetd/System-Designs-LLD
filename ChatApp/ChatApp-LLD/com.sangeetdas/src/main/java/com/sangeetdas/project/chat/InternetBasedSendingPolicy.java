package com.sangeetdas.project.chat;

import com.sangeetdas.project.messages.Message;
import com.sangeetdas.project.serverregistry.ServerRegistry;

import static com.sangeetdas.project.ChatAppLldApplication.getServerRegistry;

public class InternetBasedSendingPolicy implements ISendingPolicy {

    private ServerRegistry serverRegistry;

    public InternetBasedSendingPolicy() {
        this.serverRegistry = getServerRegistry();
    }

    @Override
    public void sendViaPolicy(String from, String to, Message message) {
        this.serverRegistry.notifyNewMessage(from, to, message);
    }
}

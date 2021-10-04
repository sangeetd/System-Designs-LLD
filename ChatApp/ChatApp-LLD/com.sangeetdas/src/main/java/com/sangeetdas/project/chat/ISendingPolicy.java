package com.sangeetdas.project.chat;

import com.sangeetdas.project.messages.Message;

public interface ISendingPolicy {
    void sendViaPolicy(String from, String to, Message message);
}

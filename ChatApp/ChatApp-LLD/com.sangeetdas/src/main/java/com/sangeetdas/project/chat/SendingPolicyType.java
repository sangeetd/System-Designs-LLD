package com.sangeetdas.project.chat;

import com.sangeetdas.project.serverregistry.ServerRegistry;

public enum SendingPolicyType {

    INTERNET_BASED(new InternetBasedSendingPolicy());

    private ISendingPolicy sendingPolicy;

    SendingPolicyType(ISendingPolicy sendingPolicy){
        this.sendingPolicy = sendingPolicy;
    }
    public ISendingPolicy getSendingPolicy() {
        return sendingPolicy;
    }
}

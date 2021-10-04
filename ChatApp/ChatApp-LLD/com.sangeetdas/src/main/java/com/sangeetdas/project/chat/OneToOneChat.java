package com.sangeetdas.project.chat;

import com.sangeetdas.project.messages.Message;

import java.util.*;

public class OneToOneChat implements IChat{

    private ISendingPolicy sendingPolicy;

    private final List<String> contactee;
    private final List<Message> localSavedMessages;

    public OneToOneChat(List<String> contactee) {
        this.contactee = contactee;
        this.localSavedMessages = new ArrayList<>();
    }

    public void setSendingPolicy(ISendingPolicy sendingPolicy) {
        this.sendingPolicy = sendingPolicy;
    }

    @Override
    public void sendMessage(String from, String to, Message message){
        //actual sending to other user
        this.sendingPolicy.sendViaPolicy(from, to, message);
        //local persistence
        updateMessage(message);
    }

    public void updateMessage(Message message){
        this.localSavedMessages.add(message);
    }

    public List<Message> showMessages(){
        return this.localSavedMessages;
    }

}

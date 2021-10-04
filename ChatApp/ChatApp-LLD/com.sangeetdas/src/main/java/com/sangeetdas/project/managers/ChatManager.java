package com.sangeetdas.project.managers;

import com.sangeetdas.project.chat.IChat;
import com.sangeetdas.project.chat.OneToOneChat;
import com.sangeetdas.project.chat.SendingPolicyType;
import com.sangeetdas.project.messages.Message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChatManager {

    private final Map<String, IChat> userActiveChats;

    public ChatManager() {
        this.userActiveChats = new HashMap<>();
    }

    public boolean chatWindowExist(String to){
        return this.userActiveChats.containsKey(to);
    }

    public void forwardOneToOne(String from, String to, Message message, SendingPolicyType sendingPolicyType){
        //as forward is one to one
        OneToOneChat currentChatWindow = null;
        if(chatWindowExist(to)){
            currentChatWindow = (OneToOneChat)this.userActiveChats.get(to);

        }else {
            currentChatWindow = new OneToOneChat(Arrays.asList(to));
            //add new user to active chat
            this.userActiveChats.put(to, currentChatWindow);
        }
        currentChatWindow.setSendingPolicy(sendingPolicyType.getSendingPolicy());
        currentChatWindow.sendMessage(from, to, message);
    }

    public Map<String, IChat> getUserActiveChats() {
        return userActiveChats;
    }
}

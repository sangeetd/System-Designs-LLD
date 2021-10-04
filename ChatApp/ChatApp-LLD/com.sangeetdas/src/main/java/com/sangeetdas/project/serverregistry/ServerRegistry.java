package com.sangeetdas.project.serverregistry;

import com.sangeetdas.project.chat.OneToOneChat;
import com.sangeetdas.project.exceptions.UserNotRegisteredException;
import com.sangeetdas.project.managers.ChatManager;
import com.sangeetdas.project.messages.Message;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ServerRegistry {

    private static ServerRegistry instance = null;

    private ServerRegistry(){ this.globalChatManager = new HashMap<>(); }

    public static ServerRegistry getServerInstance(){
        if(instance == null){
            instance = new ServerRegistry();
        }

        return instance;
    }

    //contactNo, chatManagers
    private final Map<String, ChatManager> globalChatManager;

    public void registerChatManager(final String userContact, final ChatManager userChatManager){
        this.globalChatManager.put(userContact, userChatManager);
    }

    public boolean globalUserExist(String contactNo){
        return this.globalChatManager.containsKey(contactNo);
    }

    public void notifyNewMessage(String from, String to, Message message) {

        if(!globalUserExist(to)){
            throw new UserNotRegisteredException();
        }

        ChatManager toUserChatManager = this.globalChatManager.get(to);
        OneToOneChat toOneToOneChat = null;
        if(toUserChatManager.chatWindowExist(from)){
            toOneToOneChat = (OneToOneChat) toUserChatManager.getUserActiveChats().get(from);
        }else{
            toOneToOneChat = new OneToOneChat(Arrays.asList(from));
            toUserChatManager.getUserActiveChats().put(from, toOneToOneChat);
        }

        toOneToOneChat.updateMessage(message);

    }
}

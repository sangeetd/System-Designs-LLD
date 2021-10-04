package com.sangeetdas.project.chat;

import com.sangeetdas.project.messages.Message;

import java.util.List;

public interface IChat {

    void sendMessage(String from, String to, Message message);
    void updateMessage(Message message);
    List<Message> showMessages();
}

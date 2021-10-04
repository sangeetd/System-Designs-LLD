package com.sangeet.project.model;

import com.sangeet.project.subscriber.ISubscriber;

import java.util.Queue;
import java.util.Set;

public interface ITopic {

    void registerSubscriberOfTopic(ISubscriber subscriber);
    void addMessageToTopic(String publisherID, Message message);
    Set<String> getQueueNames();
    Queue<Message> getQueueByQueueName(String queueName);

}

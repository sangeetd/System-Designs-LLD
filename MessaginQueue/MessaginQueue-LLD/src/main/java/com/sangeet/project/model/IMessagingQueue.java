package com.sangeet.project.model;

public interface IMessagingQueue {

    public MessaginQueueInfo getMessaginQueueInfo();

    void addTopic(String publisherID, Topic topic);
    Topic getTopic(String topicID);
}

package com.sangeet.project.subscriber;

import com.sangeet.project.model.IConnectToMessagingQueue;

public interface ISubscriber extends IConnectToMessagingQueue {
    String getSubscriberID();
    void subscribeToTopic(String messageQueueID, String topicID);
}

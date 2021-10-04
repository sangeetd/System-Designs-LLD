package com.sangeet.project.publisher;

import com.sangeet.project.model.IConnectToMessagingQueue;
import com.sangeet.project.model.Message;

public interface IPublisher extends IConnectToMessagingQueue {

    void createTopic(String messagingQueueID, String topicName, Integer queueInTopic);
    void publishMessage(String messagingQueueID, String topicID, Message message);

}

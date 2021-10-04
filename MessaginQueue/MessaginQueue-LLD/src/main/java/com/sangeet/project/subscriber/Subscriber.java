package com.sangeet.project.subscriber;

import com.sangeet.project.model.MessagingQueue;
import com.sangeet.project.model.Topic;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Subscriber implements ISubscriber{

    private final String subscriberID;
    private final Map<String, MessagingQueue> subscriberHoldsMessagingQueues;
    private final Map<String, Topic> subscribedTopics;

    public Subscriber() {
        this.subscriberID = UUID.randomUUID().toString();
        this.subscriberHoldsMessagingQueues = new HashMap<>();
        this.subscribedTopics = new HashMap<>();
    }

    @Override
    public String getSubscriberID() {
        return this.subscriberID;
    }

    @Override
    public void subscribeToTopic(String messageQueueID, String topicID) {
        if(!this.subscriberHoldsMessagingQueues.containsKey(messageQueueID)){
            throw new RuntimeException("Subscriber is not connected with the requested messagin queue: "+messageQueueID);
        }

        MessagingQueue messagingQueue = this.subscriberHoldsMessagingQueues.get(messageQueueID);
        Topic topic = messagingQueue.getTopic(topicID);
        if(topic != null){
            this.subscribedTopics.putIfAbsent(topic.getTopicID(), topic);
            topic.registerSubscriberOfTopic(this);
        }


    }

    @Override
    public void connectToMessagingQueue(MessagingQueue messagingQueue) {
        //holds what all messaging queue publisher can publish message to
        this.subscriberHoldsMessagingQueues.put(messagingQueue
                        .getMessaginQueueInfo()
                        .getMessagingQueueID(),
                messagingQueue);
    }
}

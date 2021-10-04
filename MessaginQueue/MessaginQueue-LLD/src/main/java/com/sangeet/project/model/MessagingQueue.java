package com.sangeet.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagingQueue implements IMessagingQueue{

    private final MessaginQueueInfo messaginQueueInfo;
    //{publisherID: List<Topic>}
    private final Map<String, List<Topic>> messagingQueueHoldsPublisherTopics;
    //{topicID, Topic}
    private final Map<String, Topic> topics;

    public MessagingQueue(String messaginQueueName, String messagingQueueVersion, String ip, String port) {
        this.messaginQueueInfo = new MessaginQueueInfo(messaginQueueName, messagingQueueVersion, ip, port);
        this.messagingQueueHoldsPublisherTopics = new HashMap<>();
        this.topics = new HashMap<>();
    }

    public MessaginQueueInfo getMessaginQueueInfo() {
        return messaginQueueInfo;
    }

    public void addTopic(String publisherID, Topic topic){
        this.messagingQueueHoldsPublisherTopics.putIfAbsent(publisherID, new ArrayList<>());
        this.messagingQueueHoldsPublisherTopics.get(publisherID).add(topic);

        this.topics.putIfAbsent(topic.getTopicID(), topic);

    }

    public Topic getTopic(String topicID){

        if(!this.topics.containsKey(topicID)){
            throw new RuntimeException("Topic is not registered");
        }

        Topic topic = this.topics.getOrDefault(topicID, null);

        return topic;

    }


}

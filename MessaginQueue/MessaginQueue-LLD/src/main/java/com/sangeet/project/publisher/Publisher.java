package com.sangeet.project.publisher;

import com.sangeet.project.model.Message;
import com.sangeet.project.model.MessagingQueue;
import com.sangeet.project.model.Topic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Publisher implements IPublisher{

    private final String publisherID;
    private final Map<String, MessagingQueue> publisherHoldsMessagingQueues;
    private final Map<String, Topic> publisherTopics;

    public Publisher() {
        this.publisherID = UUID.randomUUID().toString();
        this.publisherHoldsMessagingQueues = new HashMap<>();
        this.publisherTopics = new HashMap<>();
    }

    @Override
    public void connectToMessagingQueue(MessagingQueue messagingQueue) {
        //holds what all messaging queue publisher can publish message to
        this.publisherHoldsMessagingQueues.put(messagingQueue
                .getMessaginQueueInfo()
                .getMessagingQueueID(),
                messagingQueue);
    }

    @Override
    public void createTopic(String messagingQueueID, String topicName, Integer queueInTopic) {

        if(!this.publisherHoldsMessagingQueues.containsKey(messagingQueueID)){
            throw new RuntimeException("Unable to find messaging queue: "+messagingQueueID);
        }

        MessagingQueue messagingQueue = this.publisherHoldsMessagingQueues.get(messagingQueueID);
        Topic newTopic = new Topic(this.publisherID, topicName, queueInTopic);
        messagingQueue.addTopic(this.publisherID, newTopic);
        this.publisherTopics.put(newTopic.getTopicID(), newTopic);
    }

    @Override
    public void publishMessage(String messagingQueueID, String topicID, Message message) {
        if(!this.publisherHoldsMessagingQueues.containsKey(messagingQueueID)){
            throw new RuntimeException("Unable to found messaging queue: "+messagingQueueID);
        }

        MessagingQueue messagingQueue = this.publisherHoldsMessagingQueues.get(messagingQueueID);
        Topic topic = messagingQueue.getTopic(topicID);

        if(topic == null){
            throw new RuntimeException("Unable to publish message to topic: "+topicID);
        }

        topic.addMessageToTopic(this.publisherID, message);

    }

    public String getPublisherID() {
        return publisherID;
    }

    public Set<String> getAllMessagingQueueOfPublisher(){
        return this.publisherHoldsMessagingQueues.keySet();
    }

}

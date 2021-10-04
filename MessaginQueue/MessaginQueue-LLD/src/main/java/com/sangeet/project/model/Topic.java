package com.sangeet.project.model;

import com.sangeet.project.subscriber.ISubscriber;

import java.util.*;

public class Topic implements ITopic{

    private String publisherID;
    private String topicID;
    private String topicName;
    private Integer queuesRequired;
    private Map<String, Queue<Message>> queues;
    private Map<String, ISubscriber> subscribers;
    private final Integer MAX_QUEUE_CAPACITY = 10;

    public Topic(String publisherID, String topicName, Integer queuesRequired) {
        this.publisherID = publisherID;
        this.topicID = UUID.randomUUID().toString();
        this.topicName = topicName;
        this.queuesRequired = queuesRequired;
        initTopic();
    }

    private void initTopic(){
        if(this.queuesRequired > MAX_QUEUE_CAPACITY){
            throw new RuntimeException("Number of queues requested is more than the capacity");
        }
        this.queues = new HashMap<>(this.queuesRequired);
        for(int i = 0; i < this.queuesRequired; i++){
            queues.put(this.topicName+"_QUEUE_"+(i+1), new LinkedList<>());
        }

        this.subscribers = new HashMap<>();

    }


    @Override
    public void registerSubscriberOfTopic(ISubscriber subscriber) {
        if(this.subscribers.containsKey(subscriber.getSubscriberID())){
            throw new RuntimeException("Subscriber already registered");
        }
        this.subscribers.put(subscriber.getSubscriberID(), subscriber);
    }

    public void addMessageToTopic(String publisherID, Message message){

        if(!publisherID.equals(this.getTopicOwner())){
            throw new RuntimeException("Unknown publisher");
        }

        for(String queueName: getQueueNames()){
            this.queues.get(queueName).add(message);
        }

    }

    public String getTopicOwner(){
        return this.publisherID;
    }

    public String getTopicID() {
        return topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public Integer getQueuesRequired() {
        return queuesRequired;
    }

    public Set<String> getQueueNames(){
        return this.queues.keySet();
    }

    @Override
    public Queue<Message> getQueueByQueueName(String queueName) {
        if(!this.queues.containsKey(queueName)){
            throw new RuntimeException("Unable to connect to queue: "+queueName);
        }
        return this.queues.get(queueName);
    }

}

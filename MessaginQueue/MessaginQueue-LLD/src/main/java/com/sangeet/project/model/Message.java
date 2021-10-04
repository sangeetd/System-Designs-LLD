package com.sangeet.project.model;

import java.util.UUID;

public class Message {

    private final String messageID;
    private final String topicID;
    private final String publisherID;

    private String message;

    public Message(String topicID, String publisherID, String message) {
        this.messageID = UUID.randomUUID().toString();
        this.topicID = topicID;
        this.publisherID = publisherID;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getTopicID() {
        return topicID;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public String getMessage() {
        return message;
    }
}

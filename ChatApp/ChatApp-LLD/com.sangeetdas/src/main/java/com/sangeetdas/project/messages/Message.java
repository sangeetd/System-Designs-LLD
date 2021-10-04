package com.sangeetdas.project.messages;

import java.util.UUID;

import static com.sangeetdas.project.util.Util.getCurrentDate;

public class Message {

    private String id;
    private MultimediaContent multimediaContent;
    private String messageText;
    private String messageTimestamp;
    private String messageFrom;
    private String messageTo;

    public Message() {
        this.id = UUID.randomUUID().toString();
        this.messageTimestamp = getCurrentDate();
    }

    public Message(String messageText,
                   MultimediaContent multimediaContent,
                   String messageFrom, String messageTo) {
        this();
        this.messageText = messageText;
        this.multimediaContent = multimediaContent;
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
    }

    public Message(String messageText,
                   String messageFrom, String messageTo) {
        this();
        this.messageText = messageText;
        this.multimediaContent = null;
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
    }

    public String getId() {
        return id;
    }

    public String getMessageTimestamp() {
        return messageTimestamp;
    }

    public String getMessageText(){
        return messageText;
    }

    public boolean hasMultimediaContent(){
        return multimediaContent != null;
    }

    public MultimediaContent getMultimediaContent() { return this.multimediaContent; }

    public String getMessageFrom() {
        return messageFrom;
    }

    public String getMessageTo() {
        return messageTo;
    }
}

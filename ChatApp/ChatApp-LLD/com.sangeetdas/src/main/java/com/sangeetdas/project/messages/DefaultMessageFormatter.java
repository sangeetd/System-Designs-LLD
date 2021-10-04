package com.sangeetdas.project.messages;

public class DefaultMessageFormatter implements IMessageFormatter{

    public String format(Message msg){
        return msg.getMessageFrom()+": "+msg.getMessageText()+
                (msg.hasMultimediaContent() ?
                        "\n"+msg.getMultimediaContent().getMultimediaUrl()+" | "+msg.getMultimediaContent().getMultiMediaType().name()
                        : "")+
                "\n"+
                msg.getMessageTimestamp()+
                "\n---------------------------------------------------------------------------";
    }

}

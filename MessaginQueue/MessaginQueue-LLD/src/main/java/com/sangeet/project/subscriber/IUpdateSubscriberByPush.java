package com.sangeet.project.subscriber;

import com.sangeet.project.model.Message;

public interface IUpdateSubscriberByPush {
    void update(String topicID, Message message);
}

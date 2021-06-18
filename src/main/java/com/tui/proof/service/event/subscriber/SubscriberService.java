package com.tui.proof.service.event.subscriber;

import com.tui.proof.service.event.Topic;
import com.tui.proof.service.event.message.Message;

import java.util.List;

public abstract class SubscriberService {

    public abstract void onMessage(Message message);

    public abstract List<Topic> getTopics();
}

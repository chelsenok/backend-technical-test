package com.tui.proof.pubsub.subscriber;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.Message;

import java.util.List;

public abstract class SubscriberService {

    public abstract void onMessage(Message message);

    public abstract List<Topic> getTopics();
}

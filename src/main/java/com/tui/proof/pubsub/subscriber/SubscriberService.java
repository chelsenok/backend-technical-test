package com.tui.proof.pubsub.subscriber;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.Message;

import java.util.List;
import java.util.Observable;

public abstract class SubscriberService extends Observable {

    public void onMessage(Message message) {
        setChanged();
        notifyObservers(message);
    }

    public abstract List<Topic> getTopics();
}

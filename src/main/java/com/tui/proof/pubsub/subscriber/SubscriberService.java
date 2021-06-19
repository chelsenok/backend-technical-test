package com.tui.proof.pubsub.subscriber;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.pubsub.message.Status;

import java.util.List;
import java.util.Observable;

public abstract class SubscriberService extends Observable {

    public void onMessage(Message message) {
        Status status = null;
        try {
            processMessage(message);
            status = Status.SUCCESS;
        } catch (Exception e) {
            message.setException(e.toString());
            status = Status.FAILED;
        } finally {
            message.setStatus(status);
        }
        setChanged();
        notifyObservers(message);
    }

    protected abstract void processMessage(Message message);

    public abstract List<Topic> getTopics();
}

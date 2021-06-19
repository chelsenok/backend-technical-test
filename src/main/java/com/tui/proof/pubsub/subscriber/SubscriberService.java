package com.tui.proof.pubsub.subscriber;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.pubsub.message.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Observable;

@Slf4j
public abstract class SubscriberService extends Observable {

    public void onMessage(Message message) {
        Status status = null;
        try {
            processMessage(message);
            status = Status.SUCCESS;
        } catch (Exception e) {
            log.error("Message was failed to process: {}", message, e);
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

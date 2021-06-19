package com.tui.proof.pubsub.subscriber;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.pubsub.message.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Observable;

/**
 * Subscriber service
 */
@Slf4j
public abstract class SubscriberService extends Observable {

    /**
     * Lifecycle method to process message fetched from the pub/sub queue.
     *
     * @param message message to be processed
     */
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

    /**
     * Process message method
     *
     * @param message message to be processed
     */
    protected abstract void processMessage(Message message);

    /**
     * Get topics on which certain subscriber is listening to
     *
     * @return list of subscribed topics
     */
    public abstract List<Topic> getTopics();
}

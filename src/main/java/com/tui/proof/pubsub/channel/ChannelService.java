package com.tui.proof.pubsub.channel;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.pubsub.subscriber.SubscriberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Channel service for organizing queue of specific topic messages
 */
@Slf4j
public abstract class ChannelService {

    private final List<SubscriberService> subscribers = new ArrayList<>();
    private final ConcurrentLinkedQueue<Message> messageQueue = new ConcurrentLinkedQueue<>();

    /**
     * Save message in queue
     *
     * @param properties message properties
     * @return generated unique uuid
     */
    public UUID sendMessage(Map<Object, Object> properties) {
        Message message = composeMessage(getTopic(), properties);
        log.info("Composed {} with props: {}", message.getClass().getSimpleName(), properties);
        messageQueue.add(message);
        return message.getUuid();
    }

    /**
     * Registration of subscriber
     *
     * @param subscriber subscriber to be registered for certain channel
     */
    public void registerSubscriber(SubscriberService subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * Scheduled procedure for reading messages from the queue
     */
    @Scheduled(fixedRateString = "${api.event.channel.read-rate-millis}")
    private void readMessageQueue() {
        for (Message message; (message = messageQueue.poll()) != null; ) {
            log.info("Read message: {}", message.getUuid());
            for (SubscriberService subscriber : subscribers) {
                subscriber.onMessage(message);
            }
        }
    }

    /**
     * Compose message by properties
     *
     * @param topic      topic
     * @param properties properties
     * @return composed message of child type
     */
    public abstract Message composeMessage(Topic topic, Map<Object, Object> properties);

    /**
     * Get topic of this channel
     *
     * @return channel topic
     */
    public abstract Topic getTopic();
}

package com.tui.proof.pubsub.channel;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.Message;
import com.tui.proof.pubsub.subscriber.SubscriberService;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class ChannelService {

    private final List<SubscriberService> subscribers = new ArrayList<>();
    private final ConcurrentLinkedQueue<Message> messageQueue = new ConcurrentLinkedQueue<>();

    public UUID sendMessage(Map<Object, Object> properties) {
        Message message = composeMessage(getTopic(), properties);
        messageQueue.add(message);
        return message.getUuid();
    }

    public void registerSubscriber(SubscriberService subscriber) {
        subscribers.add(subscriber);
    }

    @Scheduled(fixedRateString = "${api.event.channel.read-rate-millis}")
    private void readMessageQueue() {
        for (Message message; (message = messageQueue.poll()) != null; ) {
            for (SubscriberService subscriber : subscribers) {
                subscriber.onMessage(message);
            }
        }
    }

    public abstract Message composeMessage(Topic topic, Map<Object, Object> properties);

    public abstract Topic getTopic();
}

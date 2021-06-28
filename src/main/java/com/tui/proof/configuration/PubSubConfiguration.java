package com.tui.proof.configuration;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.channel.ChannelService;
import com.tui.proof.pubsub.subscriber.SubscriberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.stream.Collectors;

/**
 * Configuration for pub/sub event architecture.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@ComponentScan("com.tui.proof.pubsub")
public class PubSubConfiguration {

    private final List<ChannelService> channels;
    private final List<SubscriberService> subscribers;
    private final List<Observer> observers;

    /**
     * Registration of subscribers to the specific channels by topics.
     */
    @PostConstruct
    private void registerSubscribers() {
        Map<Topic, ChannelService> channelsByTopics = channels.stream()
                .collect(Collectors.toMap(ChannelService::getTopic, channel -> channel));

        for (SubscriberService subscriber : subscribers) {
            for (Topic topic : subscriber.getTopics()) {
                ChannelService channel = channelsByTopics.get(topic);
                channel.registerSubscriber(subscriber);
                log.info("{} registered as subscriber to {}", subscriber.getClass().getSimpleName(), channel.getClass().getSimpleName());
            }
        }
    }

    /**
     * Adding observers for the processed messages notification from subscribers.
     */
    @PostConstruct
    private void addObservers() {
        for (SubscriberService subscriber : subscribers) {
            for (Observer observer : observers) {
                subscriber.addObserver(observer);
                log.info("{} added as observer to {}", observer.getClass().getSimpleName(), subscriber.getClass().getSimpleName());
            }
        }
    }
}

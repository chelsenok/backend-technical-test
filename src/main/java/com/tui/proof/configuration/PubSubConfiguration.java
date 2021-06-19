package com.tui.proof.configuration;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.channel.ChannelService;
import com.tui.proof.pubsub.subscriber.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@ComponentScan("com.tui.proof.pubsub")
public class PubSubConfiguration {

    private final List<ChannelService> channels;
    private final List<SubscriberService> subscribers;
    private final List<Observer> observers;

    @PostConstruct
    public void registerSubscribers() {
        Map<Topic, ChannelService> channelsByTopics = channels.stream()
                .collect(Collectors.toMap(ChannelService::getTopic, channel -> channel));

        for (SubscriberService subscriber : subscribers) {
            for (Topic topic : subscriber.getTopics()) {
                channelsByTopics.get(topic).registerSubscriber(subscriber);
            }
        }
    }

    @PostConstruct
    public void addObservers() {
        for (SubscriberService subscriber : subscribers) {
            for (Observer observer : observers) {
                subscriber.addObserver(observer);
            }
        }
    }
}

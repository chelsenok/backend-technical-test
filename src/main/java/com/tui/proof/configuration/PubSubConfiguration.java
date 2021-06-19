package com.tui.proof.configuration;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.channel.ChannelService;
import com.tui.proof.pubsub.subscriber.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class PubSubConfiguration {

    private final List<ChannelService> channels;
    private final List<SubscriberService> subscribers;

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
}

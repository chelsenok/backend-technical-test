package com.tui.proof.configuration;

import com.tui.proof.service.event.Topic;
import com.tui.proof.service.event.channel.ChannelService;
import com.tui.proof.service.event.subscriber.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class EventConfiguration {

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

package com.tui.proof.pubsub;

import com.tui.proof.pubsub.channel.ChannelService;
import com.tui.proof.pubsub.message.PublishedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Publish service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PublisherService {

    private final List<ChannelService> channels;

    /**
     * Publish message to the channel by specified topic
     *
     * @param topic topic of the channel
     * @param props properties
     * @return list of published messages
     */
    public List<PublishedMessage> publish(Topic topic, Map<Object, Object> props) {
        return channels.stream()
                .filter(channelService -> channelService.getTopic().equals(topic))
                .map(channelService -> new PublishedMessage(
                        channelService.sendMessage(props),
                        topic,
                        channelService.getClass().getSimpleName()
                ))
                .peek(message -> log.info("PublishedMessage was generated: {}", message))
                .collect(Collectors.toList());
    }
}

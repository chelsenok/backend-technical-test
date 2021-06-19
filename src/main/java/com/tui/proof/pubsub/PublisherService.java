package com.tui.proof.pubsub;

import com.tui.proof.pubsub.channel.ChannelService;
import com.tui.proof.pubsub.message.PublishedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final List<ChannelService> channels;

    public List<PublishedMessage> publish(Topic topic, Map<Object, Object> props) {
        return channels.stream()
                .filter(channelService -> channelService.getTopic().equals(topic))
                .map(channelService -> new PublishedMessage(
                        channelService.sendMessage(props),
                        channelService.getClass().getSimpleName()
                ))
                .collect(Collectors.toList());
    }
}

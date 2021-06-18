package com.tui.proof.service.event;

import com.tui.proof.service.event.channel.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final List<ChannelService> channels;

    public void publish(Topic topic, Map<Object, Object> props) {
        channels.stream()
                .filter(channelService -> channelService.getTopic().equals(topic))
                .forEach(channelService -> channelService.sendMessage(props));
    }
}

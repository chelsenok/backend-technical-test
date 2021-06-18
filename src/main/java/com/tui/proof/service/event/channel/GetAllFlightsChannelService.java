package com.tui.proof.service.event.channel;

import com.tui.proof.service.event.Topic;
import com.tui.proof.service.event.message.GetAllFlightsMessage;
import com.tui.proof.service.event.message.Message;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class GetAllFlightsChannelService extends ChannelService {

    @Override
    public Message composeMessage(Map<Object, Object> properties) {
        return new GetAllFlightsMessage(UUID.randomUUID());
    }

    @Override
    public Topic getTopic() {
        return Topic.GET_ALL_FLIGHTS;
    }
}

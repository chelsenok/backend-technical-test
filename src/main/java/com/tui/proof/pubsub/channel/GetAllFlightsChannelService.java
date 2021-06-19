package com.tui.proof.pubsub.channel;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.GetAllFlightsMessage;
import com.tui.proof.pubsub.message.Message;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class GetAllFlightsChannelService extends ChannelService {

    @Override
    public Message composeMessage(Topic topic, Map<Object, Object> properties) {
        return new GetAllFlightsMessage(UUID.randomUUID(), getTopic());
    }

    @Override
    public Topic getTopic() {
        return Topic.GET_ALL_FLIGHTS;
    }
}

package com.tui.proof.pubsub.channel;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.GetFlightByUuidMessage;
import com.tui.proof.pubsub.message.Message;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class GetFlightByUuidChannelService extends ChannelService {

    @Override
    public Message composeMessage(Map<Object, Object> properties) {
        return new GetFlightByUuidMessage(UUID.randomUUID(), (UUID) properties.get("uuid"));
    }

    @Override
    public Topic getTopic() {
        return Topic.GET_FLIGHT_BY_UUID;
    }
}

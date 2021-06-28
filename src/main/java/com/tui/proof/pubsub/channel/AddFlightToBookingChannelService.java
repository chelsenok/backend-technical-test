package com.tui.proof.pubsub.channel;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.AddFlightToBookingMessage;
import com.tui.proof.pubsub.message.Message;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class AddFlightToBookingChannelService extends ChannelService {

    @Override
    public Message composeMessage(Topic topic, Map<Object, Object> properties) {
        return new AddFlightToBookingMessage(UUID.randomUUID(), getTopic(), (UUID) properties.get("uuid"),
                (UUID) properties.get("availability_uuid"));
    }

    @Override
    public Topic getTopic() {
        return Topic.ADD_FLIGHT_TO_BOOKING;
    }
}

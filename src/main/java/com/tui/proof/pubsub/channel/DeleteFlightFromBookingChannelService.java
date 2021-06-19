package com.tui.proof.pubsub.channel;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.DeleteFlightFromBookingMessage;
import com.tui.proof.pubsub.message.Message;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class DeleteFlightFromBookingChannelService extends ChannelService {

    @Override
    public Message composeMessage(Topic topic, Map<Object, Object> properties) {
        return new DeleteFlightFromBookingMessage(UUID.randomUUID(), getTopic(), (UUID) properties.get("uuid"),
                (UUID) properties.get("flight_uuid"));
    }

    @Override
    public Topic getTopic() {
        return Topic.DELETE_FLIGHT_FROM_BOOKING;
    }
}

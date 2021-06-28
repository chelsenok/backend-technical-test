package com.tui.proof.pubsub.channel;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.DeleteBookingByUuidMessage;
import com.tui.proof.pubsub.message.Message;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class DeleteBookingByUuidChannelService extends ChannelService {

    @Override
    public Message composeMessage(Topic topic, Map<Object, Object> properties) {
        return new DeleteBookingByUuidMessage(UUID.randomUUID(), getTopic(), (UUID) properties.get("uuid"));
    }

    @Override
    public Topic getTopic() {
        return Topic.DELETE_BOOKING_BY_UUID;
    }
}

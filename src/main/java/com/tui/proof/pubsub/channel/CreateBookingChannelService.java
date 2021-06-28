package com.tui.proof.pubsub.channel;

import com.tui.proof.model.BookingRequest;
import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.CreateBookingMessage;
import com.tui.proof.pubsub.message.Message;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class CreateBookingChannelService extends ChannelService {

    @Override
    public Message composeMessage(Topic topic, Map<Object, Object> properties) {
        return new CreateBookingMessage(UUID.randomUUID(), getTopic(), (BookingRequest) properties.get("booking_request"));
    }

    @Override
    public Topic getTopic() {
        return Topic.CREATE_BOOKING;
    }
}

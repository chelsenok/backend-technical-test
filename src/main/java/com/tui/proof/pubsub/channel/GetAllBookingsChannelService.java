package com.tui.proof.pubsub.channel;

import com.tui.proof.pubsub.Topic;
import com.tui.proof.pubsub.message.GetAllBookingsMessage;
import com.tui.proof.pubsub.message.Message;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class GetAllBookingsChannelService extends ChannelService {

    @Override
    public Message composeMessage(Topic topic, Map<Object, Object> properties) {
        return new GetAllBookingsMessage(UUID.randomUUID(), getTopic());
    }

    @Override
    public Topic getTopic() {
        return Topic.GET_ALL_BOOKINGS;
    }
}

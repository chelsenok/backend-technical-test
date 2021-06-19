package com.tui.proof.pubsub.message;

import com.tui.proof.pubsub.Topic;

import java.util.UUID;

public class GetAllFlightsMessage extends Message {
    public GetAllFlightsMessage(UUID uuid, Topic topic) {
        super(uuid, topic);
    }
}

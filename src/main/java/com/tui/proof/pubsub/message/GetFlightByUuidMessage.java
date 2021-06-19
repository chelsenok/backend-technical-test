package com.tui.proof.pubsub.message;

import com.tui.proof.pubsub.Topic;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GetFlightByUuidMessage extends Message {

    private final UUID flightUuid;

    public GetFlightByUuidMessage(UUID uuid, Topic topic, UUID flightUuid) {
        super(uuid, topic);
        this.flightUuid = flightUuid;
    }
}

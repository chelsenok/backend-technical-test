package com.tui.proof.pubsub.message;

import com.tui.proof.model.Flight;
import com.tui.proof.pubsub.Topic;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GetFlightByUuidMessage extends Message {

    private final UUID flightUuid;
    private Flight flight;

    public GetFlightByUuidMessage(UUID uuid, Topic topic, UUID flightUuid) {
        super(uuid, topic);
        this.flightUuid = flightUuid;
    }
}

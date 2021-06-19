package com.tui.proof.pubsub.message;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tui.proof.model.Flight;
import com.tui.proof.pubsub.Topic;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetFlightByUuidMessage extends Message {

    private final UUID flightUuid;
    private Flight flight;

    public GetFlightByUuidMessage(UUID uuid, Topic topic, UUID flightUuid) {
        super(uuid, topic);
        this.flightUuid = flightUuid;
    }
}

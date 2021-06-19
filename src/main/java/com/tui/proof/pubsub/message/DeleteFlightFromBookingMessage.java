package com.tui.proof.pubsub.message;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tui.proof.pubsub.Topic;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DeleteFlightFromBookingMessage extends Message {

    private final UUID bookingUuid;
    private final UUID flightUuid;

    public DeleteFlightFromBookingMessage(UUID uuid, Topic topic, UUID bookingUuid, UUID flightUuid) {
        super(uuid, topic);
        this.bookingUuid = bookingUuid;
        this.flightUuid = flightUuid;
    }
}

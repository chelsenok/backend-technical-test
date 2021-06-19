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
public class AddFlightToBookingMessage extends Message {

    private final UUID bookingUuid;
    private final UUID availabilityUuid;

    public AddFlightToBookingMessage(UUID uuid, Topic topic, UUID bookingUuid, UUID availabilityUuid) {
        super(uuid, topic);
        this.bookingUuid = bookingUuid;
        this.availabilityUuid = availabilityUuid;
    }
}

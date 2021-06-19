package com.tui.proof.pubsub.message;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tui.proof.model.Booking;
import com.tui.proof.pubsub.Topic;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetBookingByUuidMessage extends Message {

    private final UUID bookingUuid;
    private Booking booking;

    public GetBookingByUuidMessage(UUID uuid, Topic topic, UUID bookingUuid) {
        super(uuid, topic);
        this.bookingUuid = bookingUuid;
    }
}

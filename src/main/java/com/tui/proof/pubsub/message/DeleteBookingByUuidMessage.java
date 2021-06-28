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
public class DeleteBookingByUuidMessage extends Message {

    private final UUID bookingUuid;

    public DeleteBookingByUuidMessage(UUID uuid, Topic topic, UUID bookingUuid) {
        super(uuid, topic);
        this.bookingUuid = bookingUuid;
    }
}

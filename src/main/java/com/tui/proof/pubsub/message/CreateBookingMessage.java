package com.tui.proof.pubsub.message;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tui.proof.model.Booking;
import com.tui.proof.model.BookingRequest;
import com.tui.proof.pubsub.Topic;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateBookingMessage extends Message {

    private final BookingRequest bookingRequest;
    private Booking booking;

    public CreateBookingMessage(UUID uuid, Topic topic, BookingRequest bookingRequest) {
        super(uuid, topic);
        this.bookingRequest = bookingRequest;
    }
}

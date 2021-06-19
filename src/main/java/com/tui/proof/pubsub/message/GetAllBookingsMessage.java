package com.tui.proof.pubsub.message;

import com.tui.proof.model.Booking;
import com.tui.proof.pubsub.Topic;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GetAllBookingsMessage extends Message {

    private List<Booking> bookings = new ArrayList<>();

    public GetAllBookingsMessage(UUID uuid, Topic topic) {
        super(uuid, topic);
    }
}

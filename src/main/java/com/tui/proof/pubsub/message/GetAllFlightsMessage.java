package com.tui.proof.pubsub.message;

import com.tui.proof.model.Flight;
import com.tui.proof.pubsub.Topic;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GetAllFlightsMessage extends Message {

    private List<Flight> flights = new ArrayList<>();

    public GetAllFlightsMessage(UUID uuid, Topic topic) {
        super(uuid, topic);
    }
}

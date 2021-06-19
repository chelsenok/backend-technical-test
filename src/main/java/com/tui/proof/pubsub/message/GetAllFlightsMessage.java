package com.tui.proof.pubsub.message;

import java.util.UUID;

public class GetAllFlightsMessage extends Message {
    public GetAllFlightsMessage(UUID uuid) {
        super(uuid);
    }
}

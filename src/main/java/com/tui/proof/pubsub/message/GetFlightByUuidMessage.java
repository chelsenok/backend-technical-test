package com.tui.proof.pubsub.message;

import lombok.Getter;

import java.util.UUID;

@Getter
public class GetFlightByUuidMessage extends Message {

    private final UUID flightUuid;

    public GetFlightByUuidMessage(UUID uuid, UUID flightUuid) {
        super(uuid);
        this.flightUuid = flightUuid;
    }
}

package com.tui.proof.pubsub.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class PublishedMessage {

    private final UUID uuid;
    private final String channel;
}

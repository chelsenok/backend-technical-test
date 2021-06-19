package com.tui.proof.pubsub.message;

import com.tui.proof.pubsub.Topic;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class PublishedMessage {

    private final UUID uuid;
    private final Topic topic;
    private final String channel;
}

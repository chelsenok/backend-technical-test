package com.tui.proof.pubsub.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Message {

    private final UUID uuid;
}
